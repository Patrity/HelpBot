package io.fireship;

import io.fireship.commands.CommandEnum;
import io.fireship.events.ReadyListener;
import io.fireship.events.SlashCommand;
import io.fireship.model.Option;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.restaction.CommandCreateAction;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {
    public static Main HELPBOT;
    public Properties appProperties = new Properties();
    public Logger logger;
    public JDA jda;
    public boolean isProduction;
    public Map<String, String> config = new HashMap<>();

    public static void main(String[] args) throws IOException {

        //Create instance of the app to reference it in other locations
        HELPBOT = new Main();
        HELPBOT.logger = org.slf4j.LoggerFactory.getLogger(Main.class);
        HELPBOT.logger.info("Fireship Helpbot Loading...");

        //check if we are running in production
        HELPBOT.isProduction = HELPBOT.productionCheck();

        //Load configuration file and grab the bot token
        String botToken = getBotToken();
        if (botToken == null) {
            HELPBOT.logger.error("Bot token not found");
            return;
        }
        HELPBOT.initBot(botToken);
        HELPBOT.registerCommands();

    }

    boolean productionCheck() {
        String token = System.getenv("token");
        return token != null;
    }

    static String getBotToken() throws IOException {
        String botToken = System.getenv("token");
        if (botToken == null) {
            HELPBOT.logger.error("Bot token not found.. Trying to load from app.properties");
            HELPBOT.initProperties();
            botToken = HELPBOT.appProperties.getProperty("bot_token");
        }
        return botToken;
    }

    //load app.properties from the java resources directory
    void initProperties() throws IOException {
        HELPBOT.logger.info("Loading app configuration...");
        InputStream str = HELPBOT.getClass().getResourceAsStream("/app.properties");
        appProperties.load(str);
    }

    void initBot(String token) {
        HELPBOT.logger.info("Initializing bot...");
        //build the JDA instance of the bot and store it in a global variable
        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.watching("Fireship.io"))
                .addEventListeners(new ReadyListener())
                .addEventListeners(new SlashCommand())
                .build();
    }

    //Loop through commands enum and register them with discord
    void registerCommands() {
        HELPBOT.logger.info("Registering commands...");
        Arrays.asList(CommandEnum.values()).forEach(command -> {
            HELPBOT.logger.info(" - Registering " + command.getName());
            CommandCreateAction commandAction = jda.upsertCommand(command.getName(), command.getDescription());

            for (int i = 0; i < command.getOptions().size(); i++) {
                Option option = command.getOptions().get(i);
                HELPBOT.logger.info(" - - Adding option " + option.getName());
                commandAction.addOption(OptionType.STRING, option.getName(), option.getDescription(), option.isRequired());
            }
            commandAction.queue();
        });

    }
}
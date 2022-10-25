package io.fireship;

import io.fireship.commands.CommandEnum;
import io.fireship.events.ReadyListener;
import io.fireship.events.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static Main HELPBOT;
    public static Properties appProperties = new Properties();
    public static Logger logger;
    public static JDA jda;

    public static void main(String[] args) throws IOException {

        //Create instance of the app to reference it in other locations
        HELPBOT = new Main();
        logger = org.slf4j.LoggerFactory.getLogger(Main.class);
        logger.info("Fireship Helpbot Loading...");

        //Load configuration file and grab the bot token
        String botToken = getBotToken();
        if (botToken == null) {
            logger.error("Bot token not found");
            return;
        }
        HELPBOT.initBot(botToken);
        HELPBOT.registerCommands();

    }

    static String getBotToken() throws IOException {
        String botToken = System.getenv("token");
        if (botToken == null) {
            logger.error("Bot token not found.. Trying to load from app.properties");
            HELPBOT.initProperties();
            botToken = appProperties.getProperty("bot_token");
        }
        System.out.println(botToken);
        return botToken;
    }

    //load app.properties from the java resources directory
    void initProperties() throws IOException {
        logger.info("Loading app configuration...");
        InputStream str = HELPBOT.getClass().getResourceAsStream("/app.properties");
        appProperties.load(str);
    }

    void initBot(String token) {
        logger.info("Initializing bot...");
        //build the JDA instance of the bot and store it in a global variable
        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.watching("Fireship.io"))
                .addEventListeners(new ReadyListener())
                .addEventListeners(new SlashCommand())
                .build();
    }

    //
    void registerCommands() {
        logger.info("Registering commands...");
        Arrays.asList(CommandEnum.values()).forEach(command -> {
            logger.info(" - Registering " + command.getName());
            jda.upsertCommand(command.getName(), command.getDescription()).queue();
        });

    }
}
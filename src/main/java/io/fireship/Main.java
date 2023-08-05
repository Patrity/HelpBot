package io.fireship;

import io.fireship.commands.CommandEnum;
import io.fireship.events.ReadyListener;
import io.fireship.events.RoleSelect;
import io.fireship.events.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
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
    public Map<String, String> config = new HashMap<>();

    public static void main(String[] args) throws IOException {

        //Create instance of the app to reference it in other locations
        HELPBOT = new Main();
        HELPBOT.logger = org.slf4j.LoggerFactory.getLogger(Main.class);
        HELPBOT.logger.info("Fireship Helpbot Loading...");

        HELPBOT.initProperties();

        //Load configuration file and grab the bot token
        String botToken = HELPBOT.getBotToken();
        if (botToken == null) {
            HELPBOT.logger.error("Bot token not found");
            return;
        }
        HELPBOT.initBot(botToken);
        HELPBOT.registerCommands();
    }

    String getBotToken() {
        return HELPBOT.config.get("token");
    }

    //load app.properties from the java resources directory
    void initProperties() throws IOException {
        HELPBOT.logger.info("Loading app configuration...");
        InputStream str = HELPBOT.getClass().getResourceAsStream("/app.properties");
        appProperties.load(str);
        for(String key : appProperties.stringPropertyNames()) {
            HELPBOT.config.put(key, appProperties.getProperty(key));
        }
        //loop through config and print it out
        for(Map.Entry<String, String> entry : HELPBOT.config.entrySet()) {
            HELPBOT.logger.warn(entry.getKey() + " = " + entry.getValue());
        }
    }

    void initBot(String token) {
        HELPBOT.logger.info("Initializing bot...");
        //build the JDA instance of the bot and store it in a global variable
        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.watching("Fireship.io"))
                .addEventListeners(new ReadyListener())
                .addEventListeners(new SlashCommand())
                .addEventListeners(new RoleSelect())
                .build();
    }

    //Loop through commands enum and register them with discord
    void registerCommands() {
        HELPBOT.logger.info("Registering commands...");
        CommandListUpdateAction commands = jda.updateCommands();
        Arrays.asList(CommandEnum.values()).forEach(commandData -> {
            HELPBOT.logger.info(" - Registering " + commandData.getName());
            SlashCommandData command = Commands.slash(commandData.getName(), commandData.getDescription());

            commandData.getOptions().ifPresent(list -> list.forEach(option -> {
                HELPBOT.logger.info(" - - Adding option " + option.getName());
                command.addOption(option.getType(), option.getName(), option.getDescription(), option.isRequired());
            }));

            //noinspection ResultOfMethodCallIgnored
            commands.addCommands(command);
        });
        commands.queue();
    }
}
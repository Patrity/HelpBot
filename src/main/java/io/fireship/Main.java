package io.fireship;

import io.fireship.commands.CommandEnum;
import io.fireship.events.ReadyListener;
import io.fireship.events.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    public static Main HELPBOT;
    public static Properties appProperties = new Properties();
    public static JDA jda;

    public static void main(String[] args) throws IOException {

        //Create instance of the app to reference it in other locations
        HELPBOT = new Main();
        System.out.println("Fireship Helpbot Loading...");

        //Load configuration file and grab the bot token
        HELPBOT.initProperties();
        String botToken = appProperties.getProperty("bot_token");
        if (botToken == null) {
            System.out.println("Bot token not found. Please add it to app.properties");
            return;
        }
        HELPBOT.initBot(botToken);
        HELPBOT.registerCommands();

    }

    //load app.properties from the java resources directory
    void initProperties() throws IOException {
        System.out.println("Loading app configuration...");
        InputStream str = HELPBOT.getClass().getResourceAsStream("/app.properties");
        appProperties.load(str);
    }

    void initBot(String token) {
        System.out.println("Initializing bot...");
        //build the JDA instance of the bot and store it in a global variable
        jda = JDABuilder.createDefault(token)
                .setActivity(Activity.watching("Fireship.io"))
                .addEventListeners(new ReadyListener())
                .addEventListeners(new SlashCommand())
                .build();
    }

    //
    void registerCommands() {
        System.out.println("Registering commands...");
        Arrays.asList(CommandEnum.values()).forEach(command -> {
            System.out.println(" - Registering " + command.getName());
            jda.upsertCommand(command.getName(), command.getDescription()).queue();
        });

    }
}
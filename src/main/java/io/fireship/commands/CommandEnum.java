package io.fireship.commands;

import io.fireship.commands.impl.*;
import io.fireship.model.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

// Using normal names to indentify easier and account for channel replacement; Unlikely to rename?
interface Channel {
    String[] help = { "frontend-chat", "backend-chat", "machine-learning-chat", "game-dev-chat", "mathematics-chat", "systems-chat", "help"};
    String[] spam = { "bot-spam" };
    String[] all =  Stream.concat(Arrays.stream(help), Arrays.stream(spam)).toArray(String[]::new);
}

public enum CommandEnum {
    HELP("help", "Displays a message on how to use this bot.", false, new Help(), Channel.spam),
    PROHELP("prohelp", "Shows users how to unlock Fireship pro perks in Discord", false, new ProHelp(), Channel.spam),
    CODE("code", "Shows users how to share code using markdown", false, new Code(), Channel.all),
    THANK("thank", "Thanks a user for helping", false, new Thank(), Channel.help),
    SETUP_LANGUAGE_ROLES("setup-tool-roles", "Sets up the tool roles message", true, new SetupToolRoles(), null),
    BROWSER("browser", "Common browser issues", false, new Browser(), Channel.spam),
    RESPONSE("response", "Fireship response", true, new Response(), null),
    BEAN("bean", "Bean a user", true, new Bean(), null),
    PING("ping", "Returns round trip time", false, new Ping(), Channel.spam);

    private final String name, description;
    private final boolean moderatorOnly;
    private final Command command;

    CommandEnum(String name, String description, boolean moderatorOnly, Command command, String[] allowedChannels) {
        this.name = name;
        this.description = description;
        this.moderatorOnly = moderatorOnly;
        this.command = command;
    }

    //misc getters/setters
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public boolean isModeratorOnly() {
        return this.moderatorOnly;
    }
    public Command getCommand() {
        return this.command;
    }
    public Optional<List<Option>> getOptions() {
        var command = getCommand();

        if (command instanceof HasOptions) {
            return Optional.of(((HasOptions) command).getOptions());
        }

        return Optional.empty();
    }
}


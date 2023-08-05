package io.fireship.commands;

import io.fireship.commands.impl.*;
import io.fireship.model.Option;

import java.util.List;
import java.util.Optional;

public enum CommandEnum {
    HELP("help", "Displays a message on how to use this bot.", false, new Help()),
    PROHELP("prohelp", "Shows users how to unlock Fireship pro perks in Discord", false, new ProHelp()),
    CODE("code", "Shows users how to share code using markdown", false, new Code()),
    THANK("thank", "Thanks a user for helping", false, new Thank()),
    SETUP_LANGUAGE_ROLES("setup-tool-roles", "Sets up the tool roles message", true, new SetupToolRoles()),
    BROWSER("browser", "Common browser issues", false, new Browser()),
    RESPONSE("response", "Fireship response", true, new Response()),
    BEAN("bean", "Bean a user", true, new Bean()),
    PING("ping", "Returns round trip time", false, new Ping());

    private final String name, description;
    private final boolean moderatorOnly;
    private final Command command;

    CommandEnum(String name, String description, boolean moderatorOnly, Command command, Option... options) {
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


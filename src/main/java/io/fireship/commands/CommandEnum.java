package io.fireship.commands;

import io.fireship.commands.impl.Help;

public enum CommandEnum {
    HELP("help", "Displays a message on how to use this bot.", false, new Help()),
    PING("pingg", "Pong!", false, new Help());

    private final String name, description;
    private final boolean moderatorOnly;
    private final Command command;

    CommandEnum(String name, String description, boolean moderatorOnly, Command command) {
        this.name = name;
        this.description = description;
        this.moderatorOnly = moderatorOnly;
        this.command = command;
    }

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
}

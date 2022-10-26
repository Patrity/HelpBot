package io.fireship.commands;

import io.fireship.commands.impl.Code;
import io.fireship.commands.impl.Help;
import io.fireship.commands.impl.Ping;
import io.fireship.commands.impl.ProHelp;

public enum CommandEnum {
    HELP("help", "Displays a message on how to use this bot.", false, new Help()),
    PROHELP("prohelp", "Shows users how to unlock Fireship pro perks in Discord", false, new ProHelp()),
    CODE("code", "Shows users how to share code using markdown", false, new Code()),
    PING("ping", "Returns round trip time", false, new Ping());

    private final String name, description;
    private final boolean moderatorOnly;
    private final List<Option> options;
    private final Command command;

    CommandEnum(String name, String description, boolean moderatorOnly, Command command) {
    CommandEnum(String name, String description, boolean moderatorOnly, Command command, Option... options) {
        this.name = name;
        this.description = description;
        this.moderatorOnly = moderatorOnly;
        this.command = command;
        this.options = List.of(options);
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
    public List<Option> getOptions() {
        return this.options;
    }

}

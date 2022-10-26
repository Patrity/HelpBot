package io.fireship.model;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public class Option {
    private final String name, description;
    private final boolean required;
    private final OptionType type;

    public Option(String name, String description, boolean required, OptionType type) {
        this.name = name;
        this.description = description;
        this.required = required;
        this.type = type;
    }

    //Misc getters
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isRequired() {
        return required;
    }
    public OptionType getType() {
        return type;
    }
}

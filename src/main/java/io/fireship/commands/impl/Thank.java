package io.fireship.commands.impl;

import io.fireship.commands.Command;
import io.fireship.commands.HasOptions;
import io.fireship.model.Option;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.List;

public class Thank implements Command, HasOptions {

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        event.deferReply().setEphemeral(true).queue();
    }

    @Override
    public List<Option> getOptions() {
        return List.of(
                new Option("user", "The user to thank", true, OptionType.STRING)
        );
    }
}

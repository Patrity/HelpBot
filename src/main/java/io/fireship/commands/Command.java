package io.fireship.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public interface Command {
    public void onCommand(SlashCommandInteractionEvent event);
}

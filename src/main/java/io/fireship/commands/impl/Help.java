package io.fireship.commands.impl;

import io.fireship.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Help implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        event.reply("Help command coming soon!").setEphemeral(true).queue();
    }
}

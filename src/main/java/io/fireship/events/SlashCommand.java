package io.fireship.events;

import io.fireship.commands.CommandEnum;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

public class SlashCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        System.out.println("Slash command received: " + event.getName());

        Arrays.asList(CommandEnum.values()).forEach(command -> {
            if (command.getName().equals(event.getName())) {
                command.getCommand().onCommand(event);
            }
        });
    }
}

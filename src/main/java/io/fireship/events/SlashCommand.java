package io.fireship.events;

import io.fireship.Main;
import io.fireship.commands.CommandEnum;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;

import java.util.Arrays;

public class SlashCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        Main.logger.info("Slash command received: " + event.getName());

        //TODO: Remove once we are finished testing
        if (event.getMember().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("admin"))) {
            event.reply("You do not have permission to use this command.").setEphemeral(true).queue();
            return;
        }

        //loop through all the commands and find the one that matches the command name
        Arrays.asList(CommandEnum.values()).forEach(command -> {
            if (command.getName().equals(event.getName())) {

                //check if the command requires moderator permissions, and checks if the user has the permission
                if (command.isModeratorOnly() && event.getMember().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("moderator"))) {
                    event.reply("You do not have permission to use this command.").setEphemeral(true).queue();
                    return;
                }

                //issue the command
                command.getCommand().onCommand(event);
            }
        });
    }
}

package io.fireship.events;

import io.fireship.commands.CommandEnum;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;

import static io.fireship.Main.HELPBOT;

public class SlashCommand extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        HELPBOT.logger.info("Slash command received: " + event.getName());

        if (!HELPBOT.isProduction && event.getMember().getRoles().stream().noneMatch(role -> role.getName().equalsIgnoreCase("admin"))) {
            event.reply("You do not have permission to use this command.").setEphemeral(true).queue();
            return;
        }

        //loop through all the commands and find the one that matches the command name
        Arrays.asList(CommandEnum.values()).forEach(command -> {
            if (command.getName().equals(event.getName())) {

                //check if the command requires moderator permissions, and checks if the user has the permission
                if (command.isModeratorOnly() && event.getMember().getPermissions().stream().noneMatch(permission -> permission == Permission.MESSAGE_MANAGE)) {
                    event.reply("You do not have permission to use this command.").setEphemeral(true).queue();
                    return;
                }

                //issue the command
                command.getCommand().onCommand(event);
            }
        });
    }
}

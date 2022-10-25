package io.fireship.commands.impl;

import io.fireship.commands.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        //log current time when command was received
        long time = System.currentTimeMillis();
        //reply with a base message
        event.reply("Returned in ...").queue(response -> {
            //after the response, edit the message with the time difference.
            response.editOriginalFormat("Returned in " + (System.currentTimeMillis() - time) + "ms").queue();
        });
    }
}

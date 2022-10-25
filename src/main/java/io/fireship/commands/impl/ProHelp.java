package io.fireship.commands.impl;

import io.fireship.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class ProHelp implements Command {

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("How to Obtain Fireship Pro Perks")
                .setDescription("Receive access to the Fireship Pro only channels and help forums")
                .addField("Use the `/pro` command", "Submit your registered email address for verification", false)
                .setFooter("Need a pro membership? Visit https://fireship.io/pro")
                .build();
        event.replyEmbeds(embed).queue();
    }
}

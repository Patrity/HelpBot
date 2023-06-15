package io.fireship.commands.impl;

import io.fireship.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Response implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("Fireship Responses")
                .setDescription("Your issue requires elevation! Jeff usually responds within 48 hours.")
                .addField("Billing Issues", "We understand that billing issues do occur, but we are unable to help with billing issues in Discord. Please be patient and wait for Jeff to respond", false)
                .setFooter("https://fireship.io/")
                .build();
        event.replyEmbeds(embed).queue();
    }
}

package io.fireship.commands.impl;

import io.fireship.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Shop implements Command {

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {

        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("Fireship Swag", "https://swag.fireship.io")
                .setDescription("Checkout the Fireship Merch Store! https://swag.fireship.io")
                .build();

        event.replyEmbeds(embed).queue();
    }
}

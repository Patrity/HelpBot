package io.fireship.commands.impl;

import io.fireship.commands.Command;
import io.fireship.commands.CommandEnum;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Help implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0xe69138);
        embed.setTitle("Helpbot Commands");
        embed.setFooter("https://fireship.io/");
        for (CommandEnum commands : CommandEnum.values()) {
            if(!commands.isModeratorOnly())
                embed.addField("/"+commands.getName(), commands.getDescription(), true);
        }
        embed.addField("__Moderator Commands__", "The commands below are only available to moderators.", false);
        for (CommandEnum commands : CommandEnum.values()) {
            if(commands.isModeratorOnly())
                embed.addField("/"+commands.getName(), commands.getDescription(), true);
        }
        event.replyEmbeds(embed.build()).setEphemeral(true).queue();
    }
}

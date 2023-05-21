package io.fireship.commands.impl;

import io.fireship.commands.Command;
import io.fireship.data.EmojisEnum;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

public class SetupToolRoles implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("Get the roles of the tools your use!")
                .setDescription("To obtain those roles just click the menu below and select the role you want to have.")
                .build();

        StringSelectMenu.Builder selectMenu = StringSelectMenu.create("choose-role");

        for (var role : EmojisEnum.values()) {
            if (!role.type.equals(EmojisEnum.EmojiType.ROLE)) {
                continue;
            }
            selectMenu.addOption(
                    role.displayName,
                    role.displayName,
                    String.format("Click to get the %s role", role.displayName,
                    Emoji.fromFormatted(String.format("<:%s:%s>", role.identifier, role.emojiID)))
            );
        }

        event.replyEmbeds(embed).setActionRow(selectMenu.build()).queue();
    }
}

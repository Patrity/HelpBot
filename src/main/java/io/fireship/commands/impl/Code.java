package io.fireship.commands.impl;

import io.fireship.Utils;
import io.fireship.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Code implements Command {

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("How to share code")
                .setDescription(Utils.escapeMarkdown("```js\n<your code here>\n```"))
                .addField("Example language tags: ", "js, ts, java, sql, go, golang, md, rust, ruby, html, json, c++, cpp, csharp, c, kt, kotlin, py, python, php, lua", false)
                .setFooter("https://fireship.io/")
                .build();
        event.replyEmbeds(embed).queue();
    }
}

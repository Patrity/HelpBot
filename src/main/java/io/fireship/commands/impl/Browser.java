package io.fireship.commands.impl;

import io.fireship.Utils;
import io.fireship.commands.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Browser  implements Command {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        MessageEmbed embed = new EmbedBuilder()
                .setColor(0xe69138)
                .setTitle("Common Browser Issues")
                .setDescription("The issues below are common culprits for videos not loading on fireship.io. Please try the following steps and if none of them work, let us know!")
                .addField("Ad Blockers", "Our video provider detects ad blocker extensions and often disables video playback if you have them enabled. Please first try disabling your ad blocker extension if you have one.", false)
                .addField("VPN", "Users in certain countries may not have access to our video provider, so try using a VPN to another country/location if you have access to one, or disabling your VPN if it's already on.", false)
                .addField("Brave Browser", "Brave Browser has a built-in ad blocker that can cause issues with video playback. Please 'Disable Shields' in your browser for fireship.io", false)
                .setFooter("https://fireship.io/")
                .build();
        event.replyEmbeds(embed).queue();
    }
}

package io.fireship.commands.impl;

import io.fireship.commands.Command;
import io.fireship.commands.HasOptions;
import io.fireship.model.Option;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.util.List;
import java.util.Objects;

public class Bean implements Command, HasOptions {
    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        Member moderator = event.getMember();
        User beaned = Objects.requireNonNull(event.getOption("user")).getAsUser();
        var reason = event.getOption("reason");

        assert moderator != null;

        var description = new StringBuilder();

        if (reason != null) {
            description.append(String.format(
                    "<:activity:1137406861855969310> **Reason:** %s\n",
                    reason.getAsString()
            ));
        } else {
            description.append("<:activity:1137406861855969310> **Reason:** No reason.\n");
        }

        description.append(String.format(
                "<:trustedAdmin:1137406869535735850> **Moderator:** %s<:whitelistUser:1137406878033387610><:whitelistInvite:1137406875302891680><:whitelistChannel:1137406873868451901><:upvoter:1137406871435759736><:potentialDanger:1137406863349121036>",
                moderator.getAsMention()
        ));

        var formattedBeaned = new StringBuilder("<:space:1137406865299488798><:success:1137406866251591731> ");

        formattedBeaned.append(beaned.getName());

        if (!beaned.getDiscriminator().equals("0000")) {
            formattedBeaned.append("#")
                    .append(beaned.getDiscriminator());
        }

        formattedBeaned.append(" [`").append(beaned.getId()).append("`]");

        MessageEmbed response = new EmbedBuilder()
                .setColor(Role.DEFAULT_COLOR_RAW)
                .setTitle("Bean Result:")
                .setDescription(description)
                .addField(
                    "Beaned:",
                    formattedBeaned.toString(),
                    false
                )
                .build();

        event.replyEmbeds(response).queue();
    }

    @Override
    public List<Option> getOptions() {
        return List.of(
                new Option("user", "The user to bean", true, OptionType.USER),
                new Option("reason", "The reason you are beaning this user for", false, OptionType.STRING)
        );
    }
}

package io.fireship.commands.impl;

import io.fireship.commands.Command;
import io.fireship.commands.HasOptions;
import io.fireship.model.Option;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.exceptions.HierarchyException;
import net.dv8tion.jda.api.interactions.commands.OptionType;

import java.time.Duration;
import java.util.List;

public class SelfMute implements Command, HasOptions {

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        if (event.getMember().isTimedOut()) {
            event.reply("You are alread timed out.")
                    .setEphemeral(true).queue();
            return;
        }

        try {
            var timeInHours = event.getOption("time").getAsLong();
            var timeoutDuration = Duration.ofHours(timeInHours);
            event.getMember().timeoutFor(timeoutDuration).queue();
            event.reply("You have been muted for " + timeInHours + " hours :clock:")
                    .setEphemeral(true).queue();
        } catch (NullPointerException e) {
            event.reply("You must provide a time in hours to mute yourself.")
                    .setEphemeral(true).queue();
        } catch (IllegalArgumentException e) {
            event.reply("You must provide a valid time in hours to mute yourself.")
                    .setEphemeral(true).queue();
        } catch (HierarchyException e) {
            event.reply("You're ranked higher than me meaning I can't mute you.")
                    .setEphemeral(true).queue();
        }
    }

    @Override
    public List<Option> getOptions() {
        return List.of(
                new Option("time", "Time you want to be timed out in hours", true, OptionType.INTEGER)
        );
    }
}

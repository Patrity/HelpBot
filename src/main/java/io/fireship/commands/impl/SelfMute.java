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

    public static class InvalidDurationException extends Exception {
        public InvalidDurationException(String message) {
            super(message);
        }
    }

    @Override
    public void onCommand(SlashCommandInteractionEvent event) {
        if (event.getMember().isTimedOut()) {
            event.reply("You are already timed out.")
                    .setEphemeral(true).queue();
            return;
        }

        try {
            var timeAsString = event.getOption("time").getAsString();

            double timeInHours = Double.parseDouble(timeAsString);

            var timeoutDuration = getDurationFromFloat(timeInHours);
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
        } catch (InvalidDurationException e) {
            event.reply("Timeout duration cannot be less than 10 seconds")
                    .setEphemeral(true).queue();
        }
    }

    @Override
    public List<Option> getOptions() {
        return List.of(
                new Option(
                        "time",
                        "Time you want to be timed out in hours (0.1 hours = 6 minutes)",
                        true,
                        OptionType.STRING
                )
        );
    }

    public static Duration getDurationFromFloat(double durationInHours) throws InvalidDurationException {
        // 60 seconds per minute 60 minutes per hour. 3600 seconds per hour.

        long seconds = (long) (durationInHours * 3600);

        if (seconds < 10)
            throw new InvalidDurationException("Seconds was less than 10.");

        return Duration.ofSeconds(seconds);
    }
}

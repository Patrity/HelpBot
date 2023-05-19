package io.fireship.events;

import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RoleSelect extends ListenerAdapter {
    @Override
    public void onStringSelectInteraction(StringSelectInteractionEvent event) {
        if (!event.getComponentId().equals("choose-role")) {
            return;
        }

        Role role = event.getGuild().getRolesByName(event.getValues().get(0), true).get(0);

        if (event.getMember().getRoles().contains(role)) {
            event.getGuild().removeRoleFromMember(event.getMember(), role).queue();
            event.reply(String.format("Removed %s from you!", role.getAsMention())).setEphemeral(true).queue();
        } else {
            event.getGuild().addRoleToMember(event.getMember(), role).queue();
            event.reply(String.format("Added %s to you!", role.getAsMention())).setEphemeral(true).queue();
        }

    }
}

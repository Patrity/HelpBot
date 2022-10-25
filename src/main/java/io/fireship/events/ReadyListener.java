package io.fireship.events;

import io.fireship.Main;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

//Only called on launch to log that the bot is ready
public class ReadyListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent) {
            Main.logger.info("Fireship Helpbot is ready!");
        }
    }
}

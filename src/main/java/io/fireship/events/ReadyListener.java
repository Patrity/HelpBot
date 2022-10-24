package io.fireship.events;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

public class ReadyListener implements EventListener {

    @Override
    public void onEvent(GenericEvent event) {
        System.out.println("Fireship Helpbot is ready!");
    }
}

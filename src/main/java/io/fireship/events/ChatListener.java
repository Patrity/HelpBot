package io.fireship.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChatListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getChannel().getId().equalsIgnoreCase("1021516580749856778")) {
            System.out.println("Display: " + event.getMessage().getContentDisplay());
            System.out.println("Raw: " + event.getMessage().getContentRaw());
            System.out.println("Stripped: " + event.getMessage().getContentStripped());
        }
    }


}

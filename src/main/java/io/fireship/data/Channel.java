package io.fireship.data;

import java.util.List;
import java.util.stream.Stream;

// Using normal names to indentify easier and account for channel replacement; Unlikely to rename?
public interface Channel {
    List<String> help = List.of( "frontend-chat", "backend-chat", "machine-learning-chat", "game-dev-chat", "mathematics-chat", "systems-chat", "help");
    List<String> spam = List.of("bot-spam");
    List<String> all =  Stream.concat(spam.stream(), help.stream()).toList();
}

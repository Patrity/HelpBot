package io.fireship.model;

public class User {
    private final String name, discordId;
    private final long firstSeen, lastThanks;

    public User(String name, String discordID, long firstSeen, long lastThanks) {
        this.name = name;
        this.discordId = discordID;
        this.firstSeen = firstSeen;
        this.lastThanks = lastThanks;
    }

    //Misc getters
    public String getName() {
        return name;
    }
    public String getDiscordID() {
        return discordId;
    }
    public long getFirstSeen() {
        return firstSeen;
    }
    public long getLastThanks() {
        return lastThanks;
    }

}

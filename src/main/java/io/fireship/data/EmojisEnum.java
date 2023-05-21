package io.fireship.data;

public enum EmojisEnum {
    KOTLIN("Kotlin", "kotlin", "1023231394664681573", EmojiType.ROLE),
    C("C", "c_", "1029490733738110976", EmojiType.ROLE),
    RUST("Rust", "rust", "1021534169739837581", EmojiType.ROLE);

    public final String displayName;
    public final String identifier;
    public final String emojiID;
    public final EmojiType type;

    EmojisEnum(String displayName, String identifier, String discordID, EmojiType type) {
        this.displayName = displayName;
        this.identifier = identifier;
        this.emojiID = discordID;
        this.type = type;
    }

    public enum EmojiType {
        ROLE,
        MEME
    }

}



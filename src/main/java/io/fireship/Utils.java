package io.fireship;

public class Utils {

    public static String escapeMarkdown(String text) {
        return text.replace("_", "\\_")
                .replace("*", "\\*")
                .replace("~", "\\~")
                .replace("`", "\\`")
                .replace("#", "\\#")
                .replace("-", "\\-")
                .replace(">", "\\>");
    }

}

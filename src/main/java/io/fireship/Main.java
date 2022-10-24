package io.fireship;

import net.dv8tion.jda.api.JDABuilder;


public class Main {
    public static Main HELPBOT;

    public static void main(String[] args) {
        HELPBOT = new Main();
        System.out.println("Fireship Helpbot Loading...");
        JDABuilder builder = JDABuilder.createDefault("");
    }

}
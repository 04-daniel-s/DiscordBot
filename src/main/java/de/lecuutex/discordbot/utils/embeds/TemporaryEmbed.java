package de.lecuutex.discordbot.utils.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class created by yi.dnl - 03.02.2022 / 00:37
 */

public class TemporaryEmbed extends Embed {
    private int timeInSeconds = 5;

    public TemporaryEmbed(String title, String text, String description, TextChannel textChannel, Color color) {
        super(title, text, description, textChannel, color);
    }

    public TemporaryEmbed(String title, String text, String description, TextChannel textChannel, Color color, int timeInSeconds) {
        super(title, text, description, textChannel, color);
        this.timeInSeconds = timeInSeconds;
    }

    @Override
    public void send() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setFooter("Nachricht verschickt am: " + new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
        builder.setColor(getColor());
        builder.appendDescription(getText());
        builder.setTitle(getTitle());
        builder.build();

        Message message = getTextChannel().sendMessage(builder.build()).complete();

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000L * timeInSeconds);
                message.delete().queue();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }
}

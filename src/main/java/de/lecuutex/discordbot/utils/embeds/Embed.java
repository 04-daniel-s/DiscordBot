package de.lecuutex.discordbot.utils.embeds;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class created by yi.dnl - 03.02.2022 / 00:37
 */

@Getter
public abstract class Embed {
    private final String title;

    private final String text;

    private final String description;

    private final TextChannel textChannel;

    private final Color color;

    public Embed(String title, String text, String description, TextChannel textChannel, Color color) {
        this.title = title;
        this.text = text;
        this.description = description;
        this.textChannel = textChannel;
        this.color = color;
    }

    public abstract void send();
}

package de.lecuutex.discordbot.utils.embeds;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class created by yi.dnl - 03.02.2022 / 00:37
 */

public class PermanentEmbed extends Embed {

    public PermanentEmbed(String title, String text, String description, TextChannel textChannel, Color color) {
        super(title, text, description, textChannel, color);
    }

    @Override
    public void send() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setFooter("Nachricht verschickt am: " + new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
        builder.setColor(getColor());
        builder.appendDescription(getText());
        builder.setTitle(getTitle());

        getTextChannel().sendMessage(builder.build()).queue();
    }
}

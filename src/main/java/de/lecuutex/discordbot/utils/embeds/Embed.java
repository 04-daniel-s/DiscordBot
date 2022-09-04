package de.lecuutex.discordbot.utils.embeds;

import de.lecuutex.discordbot.utils.Utils;
import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.time.Instant;

/**
 * A class created by yi.dnl - 03.02.2022 / 00:37
 */

@Getter
public class Embed {

    private final EmbedBuilder builder = new EmbedBuilder();

    private final String prefix = Utils.PREFIX;

    public Embed(String title, String text, Color color) {
        builder.setTitle(title);
        builder.setColor(color);
        builder.setDescription(text);
        builder.setTimestamp(Instant.now());
    }

    public Embed(String title, String text, int r, int g, int b) {
        builder.setTitle(title);
        builder.setColor(new Color(r, g, b));
        builder.setDescription(text);
        builder.setTimestamp(Instant.now());
    }

    public Embed(String title, int r, int g, int b) {
        builder.setTitle(title);
        builder.setColor(new Color(r, g, b));
        builder.setTimestamp(Instant.now());
    }

    public Embed setAuthor(String author) {
        builder.setAuthor(author);
        return this;
    }

    public Embed setThumbnail(String url) {
        builder.setThumbnail(url);
        return this;
    }

    public Embed setFooter(String footer) {
        builder.setFooter(footer);
        return this;
    }

    public Embed addSpacer(boolean inline) {
        builder.addBlankField(inline);
        return this;
    }

    public Embed addSpacer() {
        addSpacer(false);
        return this;
    }

    public Embed addField(String name, String value, boolean inline) {
        builder.addField(new MessageEmbed.Field(name, value, inline));
        return this;
    }

    public Embed addField(String name, String value) {
        addField(name, value, false);
        return this;
    }

    public MessageEmbed build() {
        return builder.build();
    }

    public Message send(TextChannel channel) {
        return channel.sendMessage(build()).complete();
    }

    public Message send(TextChannel channel,String id, String text) {
        return channel.sendMessage(build()).setActionRow(net.dv8tion.jda.api.interactions.components.Button.success(id,text)).complete();
    }

    public static void delete(Message message, int time) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000L * time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            message.delete().queue();
        });
        thread.start();
    }
}

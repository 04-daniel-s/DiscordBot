package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import jdk.jshell.execution.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * A class created by yi.dnl - 08.02.2022 / 17:08
 */

public class GuildMessageReactionAddListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReactionAdd(GuildMessageReactionAddEvent event) {
        if (event.getChannel().getId().equals("940634940591202314")) {
            User user = event.getUser();

            if (!user.getId().equals(Utils.DANIEL_ID) && !user.isBot()) {
                event.getReaction().removeReaction().queue();
            } else if (event.getReactionEmote().getName().equals("✅") && user.getId().equals(Utils.DANIEL_ID)) {
                Message botMessage = event.getReaction().getTextChannel().retrieveMessageById(event.getMessageId()).complete();
                String content = "";

                for (MessageEmbed embed : botMessage.getEmbeds()) {
                    content = embed.getDescription();
                }

                new Embed("⛏ **|** History", Utils.PREFIX + "Datum: "
                        + new SimpleDateFormat().format(new Date(botMessage.getTimeCreated().toInstant().getEpochSecond() * 1000))
                        + "\n\n" + content, Color.decode("#6c63cd"))
                        .send(event.getGuild().getTextChannelById("947588938284027914"));

                botMessage.delete().queue();
            }
        }
    }
}

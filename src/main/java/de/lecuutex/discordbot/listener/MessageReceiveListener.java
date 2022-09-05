package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.MySQL;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import jdk.jshell.execution.Util;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;

import java.awt.*;
import java.lang.reflect.UndeclaredThrowableException;

/**
 * A class created by yi.dnl - 02.02.2022 / 23:52
 */

public class MessageReceiveListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        TextChannel channel = event.getTextChannel();
        Member member = event.getMember();

        if (Utils.getAmountOfBadWords(message.getContentRaw()) >= 10) {
            MySQL.deleteMessage(message.getId());
            event.getMessage().delete().queue();
            return;
        }

        if (channel.getId().equals("940634940591202314")) {
            if (!member.getUser().isBot() && !member.getId().equals(Utils.DANIEL_ID) && !member.getId().equals(Utils.ALEX_ID)) {
                message.delete().queue();
                return;
            }

            if(member.getUser().isBot()) return;

            Message messageEmbed = new Embed("⛏ **|** TODO", message.getContentRaw(), Color.decode("#f6b93b")).send(channel);
            messageEmbed.addReaction("✅").queue();
            message.delete().queue();
        }

        if (channel.getId().equals(Utils.LOG_CHANNEL.getId()) || channel.getId().equals("947588938284027914") || channel.getId().equals("947613530838925403")) {
            if (event.getMember().getUser().isBot()) return;
            event.getMessage().delete().queue();
        }

        MySQL.storeMessage(message);
    }
}
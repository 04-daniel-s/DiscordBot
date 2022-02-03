package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.embeds.PermanentEmbed;
import de.lecuutex.discordbot.utils.embeds.TemporaryEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

/**
 * A class created by yi.dnl - 02.02.2022 / 23:52
 */

public class MessageReceiveListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getMessage().getContentDisplay().startsWith("temp")) {
            event.getMessage().delete().queue();
            new TemporaryEmbed("Nachricht", event.getMessage().getContentDisplay().substring(4).trim(), "", event.getTextChannel(), Color.cyan).send();
        } else if (event.getMessage().getContentDisplay().startsWith("perm")) {
            event.getMessage().delete().queue();
            new PermanentEmbed("Nachricht",  event.getMessage().getContentDisplay().substring(4).trim(), "", event.getTextChannel(), Color.cyan).send();
        }
    }
}

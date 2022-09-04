package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.MySQL;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.events.message.MessageDeleteEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Locale;

/**
 * A class created by yi.dnl - 06.02.2022 / 18:32
 */

public class MessageDeleteListener extends ListenerAdapter {

    @Override
    public void onMessageDelete(MessageDeleteEvent event) {
        String id = event.getMessageId();
        String author = MySQL.getAuthor(id);
        String message = MySQL.getMessage(id);
        String url = MySQL.getLink(id);

        if (author.equals("error") || author.equals("Elefant#7353")) return;

        new Embed("📬 Gelöschte Nachricht",
                Utils.PREFIX + "Nutzer: " + author + "\n\n" + Utils.PREFIX + "Nachricht:\n" +
                        message, Color.decode("#b71540")).setThumbnail(url).send(Utils.TEST_CHANNEL, "punish", "🔴 Punish!");
        MySQL.deleteMessage(id);
    }
}

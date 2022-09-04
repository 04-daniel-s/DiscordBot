package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.MySQL;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

/**
 * A class created by yi.dnl - 06.02.2022 / 18:32
 */

public class MessageUpdateListener extends ListenerAdapter {
    //TODO: Embed -> Ban button

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        Message message = event.getMessage();

        if (message.getAuthor().getAsTag().equals("Elefant#7353")) return;

        new Embed("📬 Änderung",
                Utils.PREFIX + "Nutzer: " + message.getAuthor().getAsTag() + " *(" + message.getAuthor().getAsMention() + ")*" + "\n\n"
                        + Utils.PREFIX + "Vorher:\n" + MySQL.getMessage(message.getId()) + "\n\n"
                        + Utils.PREFIX + "Nachher:\n" + message.getContentRaw(),
                Color.decode("#b71540")).setThumbnail(message.getAuthor().getAvatarUrl()).send(Utils.TEST_CHANNEL,"punish", "🔴 Punish!");
        MySQL.storeMessage(message);
    }
}

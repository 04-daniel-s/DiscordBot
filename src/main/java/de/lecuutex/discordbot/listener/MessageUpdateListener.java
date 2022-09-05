package de.lecuutex.discordbot.listener;

import com.mysql.cj.protocol.a.MysqlBinaryValueDecoder;
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

    @Override
    public void onMessageUpdate(MessageUpdateEvent event) {
        Message message = event.getMessage();
        if (message.getAuthor().getAsTag().equals("Elefant#7353")) return;

        new Embed("📬 Änderung",
                Utils.PREFIX + "Nutzer: " + message.getAuthor().getAsTag() + "\n\n", Color.decode("#b71540"))
                .addField("Vorher", MySQL.getMessage(message.getId()) + "\n\n")
                .addField("Nachher", message.getContentRaw())
                .setThumbnail(message.getAuthor().getAvatarUrl())
                .setFooter(Utils.getAmountOfBadWords(message.getContentRaw()) + " Punkte")
                .send(Utils.TEST_CHANNEL, "punish", "🔴 Punish!");

        MySQL.storeMessage(message);
    }
}

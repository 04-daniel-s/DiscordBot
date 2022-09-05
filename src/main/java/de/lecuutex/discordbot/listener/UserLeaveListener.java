package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A class created by yi.dnl - 06.02.2022 / 05:39
 */

public class UserLeaveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(GuildMemberRemoveEvent event) {
        if (event.getUser().isBot()) return;
        Utils.sendLogEmbed(new Embed(":bust_in_silhouette: **|** Mitglied verlassen",
                Utils.PREFIX + "Name:" + event.getMember().getAsMention() + " **-** " + event.getMember().getId() + "\n" +
                        Utils.PREFIX + "Erstellt: " + event.getMember().getUser().getTimeCreated().format(DateTimeFormatter.ofPattern("dd.MM.yy, hh:mm")) + "\n" +
                        Utils.PREFIX + "Beigetreten: " + new SimpleDateFormat().format(new Date(event.getMember().getTimeJoined().toEpochSecond() / 1000)) + "\n" +
                        Utils.PREFIX + "Verlassen: " + new SimpleDateFormat().format(new Date(Instant.now().toEpochMilli())), 183, 21, 64)
                .setFooter("User left"));
    }
}

package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.DiscordBot;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * A class created by yi.dnl - 02.02.2022 / 23:57
 */

public class UserJoinListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent event) {
        Utils.sendLogEmbed(new Embed(":busts_in_silhouette: Mitglied beigetreten",
                Utils.PREFIX + "Name:" + event.getMember().getAsMention() + " **-** " + event.getMember().getId() + "\n" +
                        Utils.PREFIX + "Erstellt: " + event.getMember().getUser().getTimeCreated().format(DateTimeFormatter.ofPattern("dd.MM.yy, hh:mm")) + "\n" +
                        Utils.PREFIX + "Beigetreten: " + new SimpleDateFormat().format(new Date(Instant.now().toEpochMilli())), 243, 156, 18)
                .setFooter("User joined"));
    }
}

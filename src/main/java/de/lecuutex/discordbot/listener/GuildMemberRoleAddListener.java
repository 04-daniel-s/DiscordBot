package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * A class created by yi.dnl - 27.02.2022 / 20:39
 */

public class GuildMemberRoleAddListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Member member = event.getMember();
        Role role = event.getRoles().get(0);

        Utils.sendLogEmbed(new Embed(":page_facing_up: **|** Rangänderung",
                Utils.PREFIX + "Name: " + member.getAsMention() + "\n" +
                        Utils.PREFIX + "Rang: " + role.getName() + "\n" +
                        Utils.PREFIX + "Datum: " + new SimpleDateFormat().format(new Date(Instant.now().toEpochMilli()))
                , 120, 224, 143)
                .setFooter("Rank set by " + event.getMember().getUser().getAsTag())
                .setThumbnail(member.getUser().getAvatarUrl()));
    }
}

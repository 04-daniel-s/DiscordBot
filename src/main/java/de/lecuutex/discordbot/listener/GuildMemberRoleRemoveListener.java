package de.lecuutex.discordbot.listener;

import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * A class created by yi.dnl - 27.02.2022 / 20:39
 */

public class GuildMemberRoleRemoveListener extends ListenerAdapter {

    @Override
    public void onGuildMemberRoleRemove(GuildMemberRoleRemoveEvent event) {
        System.out.println(3);
        //TODO: g
    }
}

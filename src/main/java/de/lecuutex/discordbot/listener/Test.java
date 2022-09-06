package de.lecuutex.discordbot.listener;

import net.dv8tion.jda.api.events.message.guild.react.GuildMessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * A class created by yi.dnl - 06.09.2022 / 23:01
 */

public class Test extends ListenerAdapter {
    @Override
    public void onGuildMessageReactionAdd(@NotNull GuildMessageReactionAddEvent event) {
        event.getChannel().sendMessage("test").complete();
    }
}

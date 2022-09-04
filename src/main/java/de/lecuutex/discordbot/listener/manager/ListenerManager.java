package de.lecuutex.discordbot.listener.manager;

import de.lecuutex.discordbot.commands.manager.CommandManager;
import de.lecuutex.discordbot.listener.*;
import net.dv8tion.jda.api.JDA;

/**
 * A class created by yi.dnl - 27.02.2022 / 23:04
 */

public class ListenerManager {

    public static void registerEvents(JDA jda) {
        CommandManager commandManager = new CommandManager(jda.updateCommands());

        jda.addEventListener(new MessageReceiveListener());
        jda.addEventListener(new MessageUpdateListener());
        jda.addEventListener(new MessageDeleteListener());
        jda.addEventListener(new UserJoinListener());
        jda.addEventListener(new UserLeaveListener());
        jda.addEventListener(new GuildMessageReactionAddListener());
        jda.addEventListener(new GuildMemberRoleAddListener());
        jda.addEventListener(new GuildMemberRoleRemoveListener());
        jda.addEventListener(new ButtonClickListener());
        jda.addEventListener(commandManager);
    }
}

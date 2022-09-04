package de.lecuutex.discordbot.commands;

import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

/**
 * A class created by yi.dnl - 28.02.2022 / 17:30
 */

public class PlayCommand extends DefaultCommand {

    @Override
    public void execute(SlashCommandEvent event) {

    }

    @Override
    public boolean isAdminCommand() {
        return false;
    }

    @Override
    public String getCommand() {
        return "play";
    }
}

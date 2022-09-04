package de.lecuutex.discordbot.commands.manager;

import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.util.Objects;

/**
 * A class created by yi.dnl - 04.02.2022 / 15:47
 */

@Getter
@Setter
public abstract class DefaultCommand {

    private final String prefix = Utils.PREFIX;

    private TextChannel logChannel;

    private TextChannel currentChannel;

    private Guild guild;

    private Member member;

    private String subCommand;

    private boolean replied = false;

    public void setValues(SlashCommandEvent event) {
        logChannel = Objects.requireNonNull(Utils.LOG_CHANNEL);
        currentChannel = event.getTextChannel();
        guild = event.getGuild();
        member = event.getMember();
        subCommand = event.getSubcommandName();
    }

    public abstract void execute(SlashCommandEvent event);

    public abstract boolean isAdminCommand();

    public abstract String getCommand();

    public boolean equalsSubcommand(String command) {
        return subCommand.equals(command);
    }

    public boolean hasPermission(Permission permission) {
        return member.hasPermission(permission);
    }

    public void sendLogEmbed(Embed embed) {
        logChannel.sendMessage(embed.build()).queue();
    }

    public void sendChannelMessage(Embed embed) {
        currentChannel.sendMessage(embed.build()).queue();
    }

    public void sendChannelMessage(Embed embed, int time) {
        Embed.delete(currentChannel.sendMessage(embed.build()).complete(), time);
    }

    public void replyError(SlashCommandEvent event, String error) {
        Utils.replyError(event, error);
        setReplied(true);
    }
}

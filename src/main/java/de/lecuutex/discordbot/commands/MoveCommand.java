package de.lecuutex.discordbot.commands;

import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.Errors;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

/**
 * A class created by yi.dnl - 06.02.2022 / 17:10
 */

public class MoveCommand extends DefaultCommand {

    @Override
    public void execute(SlashCommandEvent event) {
        Member targetMember = event.getOption("user").getAsMember();
        VoiceChannel voiceChannel = getMember().getVoiceState().getChannel();

        if (event.getOption("channel") != null && event.getOption("channel").getAsGuildChannel() instanceof VoiceChannel) {
            voiceChannel = (VoiceChannel) event.getOption("channel").getAsGuildChannel();
        }

        if (!getMember().getVoiceState().inVoiceChannel()) {
            replyError(event, Errors.YOU_ARE_NOT_IN_VC.getError());
            return;
        } else if (!targetMember.getVoiceState().inVoiceChannel()) {
            replyError(event, Errors.USER_IS_NOT_IN_VC.getError());
            return;
        } else if (targetMember.getVoiceState().getChannel() == voiceChannel) {
            replyError(event, Errors.IS_IN_YOUR_VC.getError());
            return;
        }

        getGuild().moveVoiceMember(targetMember, voiceChannel).queue();

        sendLogEmbed(new Embed(":microphone2: Mitglied verschoben", getPrefix() + "Mitglied: " + targetMember.getAsMention() + "\n" +
                getPrefix() + "Vorher: " + targetMember.getVoiceState().getChannel().getAsMention() + "\n" +
                getPrefix() + "Nachher: " + voiceChannel.getAsMention(), 130, 204, 221)
                .setFooter("User moved by " + getMember().getUser().getAsTag()));
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }

    @Override
    public String getCommand() {
        return "move";
    }
}

package de.lecuutex.discordbot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.audio.AudioPlayerSendHandler;
import de.lecuutex.discordbot.utils.audio.AudioResultHandler;
import de.lecuutex.discordbot.utils.audio.TrackScheduler;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * A class created by yi.dnl - 28.02.2022 / 17:30
 */

public class PlayCommand extends DefaultCommand {
    @Override
    public void execute(SlashCommandEvent event) {
        String url = event.getOption("link").getAsString();
        VoiceChannel voiceChannel = getMember().getVoiceState().getChannel();

        if (event.getOption("channel") != null && event.getOption("channel").getAsGuildChannel() instanceof VoiceChannel) {
            voiceChannel = (VoiceChannel) event.getOption("channel").getAsGuildChannel();
        }

        AudioManager audioManager = getGuild().getAudioManager();
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(getGuild());
        player.addListener(trackScheduler);

        AudioPlayerSendHandler handler = new AudioPlayerSendHandler(player);
        playerManager.loadItem(url, new AudioResultHandler(player));
        audioManager.setSendingHandler(handler);

        audioManager.openAudioConnection(voiceChannel);
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }

    @Override
    public String getCommand() {
        return "play";
    }
}

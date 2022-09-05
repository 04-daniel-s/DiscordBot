package de.lecuutex.discordbot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.playback.AudioFrame;
import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.audio.AudioEventAdapter;
import de.lecuutex.discordbot.utils.audio.AudioPlayerSendHandler;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.Nullable;

import java.nio.ByteBuffer;

/**
 * A class created by yi.dnl - 28.02.2022 / 17:30
 */

public class PlayCommand extends DefaultCommand {
    @Override
    public void execute(SlashCommandEvent event) {
        String url = event.getOption("link").getAsString();

        VoiceChannel myChannel = getGuild().getVoiceChannelById("866371066838777866");
        AudioManager audioManager = getGuild().getAudioManager();

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();
        AudioEventAdapter audioEventAdapter = new AudioEventAdapter(getGuild());
        player.addListener(audioEventAdapter);

        AudioPlayerSendHandler handler = new AudioPlayerSendHandler(player);
        audioManager.setSendingHandler(handler);

        playerManager.loadItem(url, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                player.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                for (AudioTrack track : playlist.getTracks()) {
                    player.playTrack(track);
                }
            }

            @Override
            public void noMatches() {
                // Notify the user that we've got nothing
            }

            @Override
            public void loadFailed(FriendlyException throwable) {
                // Notify the user that everything exploded
            }
        });

        audioManager.openAudioConnection(myChannel);
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

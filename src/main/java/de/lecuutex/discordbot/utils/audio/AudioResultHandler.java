package de.lecuutex.discordbot.utils.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

/**
 * A class created by yi.dnl - 05.09.2022 / 18:03
 */

public class AudioResultHandler implements AudioLoadResultHandler {

    private final AudioPlayer player;

    public AudioResultHandler(AudioPlayer player) {
        this.player = player;
    }

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
}

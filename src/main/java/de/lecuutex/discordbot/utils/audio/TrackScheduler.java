package de.lecuutex.discordbot.utils.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

/**
 * A class created by yi.dnl - 04.09.2022 / 23:02
 */

public class TrackScheduler extends AudioEventAdapter {

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        player.playTrack(track);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {

    }
}

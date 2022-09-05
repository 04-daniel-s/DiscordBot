package de.lecuutex.discordbot.utils.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

/**
 * A class created by yi.dnl - 04.09.2022 / 23:02
 */

public class TrackScheduler extends com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter {

    private final Guild guild;

    public TrackScheduler(Guild guild) {
        this.guild = guild;
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        AudioManager audioManager = guild.getAudioManager();
        audioManager.closeAudioConnection();
    }
}

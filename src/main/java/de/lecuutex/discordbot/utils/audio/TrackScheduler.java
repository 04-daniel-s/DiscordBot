package de.lecuutex.discordbot.utils.audio;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.ArrayList;

/**
 * A class created by yi.dnl - 04.09.2022 / 23:02
 */

public class TrackScheduler extends AudioEventAdapter {

    private final Guild guild;

    private final ArrayList<String> queue = new ArrayList<>();

    private final AudioPlayerManager audioPlayerManager;

    private final AudioResultHandler audioResultHandler;

    public TrackScheduler(Guild guild, AudioPlayerManager audioPlayerManager, AudioResultHandler audioResultHandler) {
        this.guild = guild;
        this.audioPlayerManager = audioPlayerManager;
        this.audioResultHandler = audioResultHandler;
    }

    @Override
    public void onTrackStart(AudioPlayer player, AudioTrack track) {
        player.playTrack(track);
        queue.remove(track.getIdentifier());
        new Embed("Test", "Start", 0, 0, 0).send(Utils.LOG_CHANNEL);
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        if (queue.size() == 0) {
            AudioManager audioManager = guild.getAudioManager();
            audioManager.closeAudioConnection();
            return;
        }

        audioPlayerManager.loadItem(queue.get(0), new AudioResultHandler(player));
        // new Embed("Test", "End", 0, 0, 0).send(Utils.LOG_CHANNEL);
    }

    public void queue(String url) {
        queue.add(url);
    }
}

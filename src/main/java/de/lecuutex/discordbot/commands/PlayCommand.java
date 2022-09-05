package de.lecuutex.discordbot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
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

        if (voiceChannel == null) return;

        AudioManager audioManager = getGuild().getAudioManager();
        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();

        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioPlayer player = playerManager.createPlayer();

        AudioResultHandler audioResultHandler = new AudioResultHandler(player);
        audioManager.setSendingHandler(new AudioPlayerSendHandler(player));

        playerManager.loadItem(url, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                player.playTrack(track);
            }

            @Override
            public void playlistLoaded(AudioPlaylist playlist) {

            }

            @Override
            public void noMatches() {

            }

            @Override
            public void loadFailed(FriendlyException exception) {

            }
        });

        TrackScheduler trackScheduler = new TrackScheduler();
        player.addListener(trackScheduler);

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

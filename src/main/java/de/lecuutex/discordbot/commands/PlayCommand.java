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
import de.lecuutex.discordbot.utils.audio.AudioResultHandler;
import de.lecuutex.discordbot.utils.audio.TrackScheduler;
import de.lecuutex.discordbot.utils.audio.AudioPlayerSendHandler;
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

        VoiceChannel myChannel = getGuild().getVoiceChannelById("866371066838777866");
        AudioManager audioManager = getGuild().getAudioManager();

        AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
        AudioSourceManagers.registerRemoteSources(playerManager);

        AudioPlayer player = playerManager.createPlayer();
        TrackScheduler trackScheduler = new TrackScheduler(getGuild());
        player.addListener(trackScheduler);

        AudioPlayerSendHandler handler = new AudioPlayerSendHandler(player);
        playerManager.loadItem(url, new AudioResultHandler(player));
        audioManager.setSendingHandler(handler);

        audioManager.openAudioConnection(myChannel);
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

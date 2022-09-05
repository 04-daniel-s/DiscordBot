package de.lecuutex.discordbot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.audio.AudioPlayerSendHandler;
import de.lecuutex.discordbot.utils.audio.AudioResultHandler;
import de.lecuutex.discordbot.utils.audio.TrackScheduler;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.audio.hooks.ConnectionStatus;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * A class created by yi.dnl - 28.02.2022 / 17:30
 */

public class PlayCommand extends DefaultCommand {

    private TrackScheduler trackScheduler = null;

    @Override
    public void execute(SlashCommandEvent event) {
        String url = event.getOption("link").getAsString();
        VoiceChannel voiceChannel = getMember().getVoiceState().getChannel();

        if (event.getOption("channel") != null && event.getOption("channel").getAsGuildChannel() instanceof VoiceChannel) {
            voiceChannel = (VoiceChannel) event.getOption("channel").getAsGuildChannel();
        }

        if(voiceChannel == null) return;

        AudioManager audioManager = getGuild().getAudioManager();

        if (audioManager.getConnectionStatus() != ConnectionStatus.CONNECTED) {
            new Embed("test","nicht connected",0,0,0).send(Utils.LOG_CHANNEL);
            AudioPlayerManager playerManager = new DefaultAudioPlayerManager();
            AudioSourceManagers.registerRemoteSources(playerManager);

            AudioPlayer player = playerManager.createPlayer();
            trackScheduler = new TrackScheduler(getGuild(), playerManager);
            trackScheduler.queue(url);
            player.addListener(trackScheduler);

            AudioPlayerSendHandler handler = new AudioPlayerSendHandler(player);
            playerManager.loadItem(url, new AudioResultHandler(player));
            audioManager.setSendingHandler(handler);

            audioManager.openAudioConnection(voiceChannel);
            return;
        }

        trackScheduler.queue(url);
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

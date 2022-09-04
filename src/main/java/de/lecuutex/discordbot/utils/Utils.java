package de.lecuutex.discordbot.utils;

import de.lecuutex.discordbot.DiscordBot;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

/**
 * A class created by yi.dnl - 06.02.2022 / 03:46
 */

public class Utils {

    public static final String DANIEL_ID = "344851703369170954";

    public static final String ALEX_ID = "417340639357960203";

    public static final String TOKEN = "ODY2NjM3NjM1MTk0NzE2MTYx.YPVdMQ.DU5MDoYGYmlqqgSROtFzKcUTUcw";

    public static final TextChannel TEST_CHANNEL = DiscordBot.getInstance().getJda().getTextChannelById("866636451671310336");

    public static final TextChannel LOG_CHANNEL = DiscordBot.getInstance().getJda().getTextChannelById("939914634062884884");

    public static final TextChannel MESSAGES_CHANNEL = DiscordBot.getInstance().getJda().getTextChannelById("947613530838925403");

    public static final String PREFIX = "• ";

    public static void replyError(SlashCommandEvent event, String error) {
        event.replyEmbeds(new Embed(":warning: Fehler aufgetreten", error, 12, 36, 97).build()).setEphemeral(true).queue();
    }

    public static String muteRoleID = "949625765752868944";

    public static void sendLogEmbed(Embed embed) {
        Utils.LOG_CHANNEL.sendMessage(embed.build()).queue();
    }

    public static int getAmountOfBadWords(String sentence) {
        int amount = 0;

        for (BadWords words : BadWords.values()) {
            if (!sentence.toLowerCase().contains(words.getWord())) continue;
            amount += words.getAmount();
        }

        return amount;
    }
}

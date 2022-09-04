package de.lecuutex.discordbot.commands;

import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.Errors;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.exceptions.ContextException;

import java.rmi.server.ExportException;
import java.util.List;

/**
 * A class created by yi.dnl - 04.02.2022 / 15:47
 */

public class ChatClearCommand extends DefaultCommand {

    @Override
    public void execute(SlashCommandEvent event) {
        TextChannel channel = event.getTextChannel();

        int amount = Math.min(Math.toIntExact(event.getOption("amount").getAsLong()), 100);

        List<Message> messageList = event.getTextChannel().getHistory().retrievePast(amount).complete();

        if (messageList.size() < 2) {
            replyError(event, Errors.CC_AMOUNT_MIN.getError());
            return;
        }

        channel.deleteMessages(messageList).queue();
        sendChannelMessage(new Embed(":wastebasket: **|** ChatClear",
                event.getUser().getAsMention() + " hat `" + messageList.size() + "` Nachrichten gelöscht!", 52, 152, 219), 5);
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }

    @Override
    public String getCommand() {
        return "chatclear";
    }
}

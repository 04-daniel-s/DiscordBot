package de.lecuutex.discordbot.listener;

import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A class created by yi.dnl - 05.03.2022 / 12:07
 */

public class ButtonClickListener extends ListenerAdapter {

    @Override
    public void onButtonClick(ButtonClickEvent event) {
        Guild guild = event.getGuild();
        guild.loadMembers();

        MessageEmbed messageEmbed = event.getTextChannel().retrieveMessageById(event.getMessageId()).complete().getEmbeds().get(0);
        String userTag = "";

        for (String string : messageEmbed.getDescription().split("\n")) {
            if (!string.contains("Nutzer")) continue;
            userTag = string.split(" ")[string.split(" ").length - 1].trim();
        }

        if (event.getComponentId().equals("punish")) {
            String finalUserTag = userTag;

            Member member = guild.getMembers().stream().filter(m -> m.getUser().getAsTag().equals(finalUserTag)).findAny().get();
            Role muteRole = guild.getRoleById(Utils.muteRoleID);

            if (!member.getRoles().contains(muteRole)) guild.addRoleToMember(member, muteRole).queue();
            event.reply("").complete().deleteOriginal().queue();

            event.getTextChannel().editMessageEmbedsById(event.getMessageId(), new Embed("test", "test", 22, 43, 95).build());
        }
    }
}

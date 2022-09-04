package de.lecuutex.discordbot.commands;

import de.lecuutex.discordbot.commands.manager.DefaultCommand;
import de.lecuutex.discordbot.utils.Errors;
import de.lecuutex.discordbot.utils.embeds.Embed;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.stream.Collectors;

/*
 * A class created by yi.dnl - 06.02.2022 / 01:50
 */

public class RankCommand extends DefaultCommand {

    @Override
    public String getCommand() {
        return "rank";
    }

    @Override
    public void execute(SlashCommandEvent event) {
        Member member = event.getOption("user").getAsMember();
        Role role = event.getOption("rank").getAsRole();

        if(role.getName().equals("Zebra")) {
            replyError(event,Errors.INSUFFICIENT_PERMISSIONS.getError());
            return;
        }

        if (equalsSubcommand("set")) {
            if (member.getRoles().stream().anyMatch(r -> r == role)) {
                replyError(event, Errors.HAS_ALREADY_THIS_ROLE.getError());
                return;
            }

            getGuild().addRoleToMember(member, role).queue();

            sendLogEmbed(new Embed(":page_facing_up: **|** Rangänderung",
                    getPrefix() + "Name: " + member.getAsMention() + "\n" +
                            getPrefix() + "Rang: " + role.getName() + "\n" +
                            getPrefix() + "Datum: " + new SimpleDateFormat().format(new Date(Instant.now().toEpochMilli()))
                    , 120, 224, 143)
                    .setFooter("Rank set by " + event.getMember().getUser().getAsTag())
                    .setThumbnail(member.getUser().getAvatarUrl()));

        } else if (equalsSubcommand("remove")) {
            if (member.getRoles().stream().noneMatch(r -> r.getName().equals(role.getName()))) {
                replyError(event, Errors.HAS_NOT_THIS_ROLE.getError());
                return;
            }

            getGuild().removeRoleFromMember(member, role).queue();

            sendLogEmbed(new Embed(":page_facing_up: **|** Rangänderung",
                    getPrefix() + "Name: " + member.getAsMention() + "\n" +
                            getPrefix() + "Rang: " + role.getName() + "\n" +
                            getPrefix() + "Datum: " + new SimpleDateFormat().format(new Date(Instant.now().toEpochMilli()))
                    , 229, 80, 57)
                    .setFooter("Rank removed by " + event.getMember().getUser().getAsTag())
                    .setThumbnail(member.getUser().getAvatarUrl()));
        }
    }

    @Override
    public boolean isAdminCommand() {
        return true;
    }
}

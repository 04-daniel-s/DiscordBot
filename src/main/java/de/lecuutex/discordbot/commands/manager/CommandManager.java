package de.lecuutex.discordbot.commands.manager;

import de.lecuutex.discordbot.commands.ChatClearCommand;
import de.lecuutex.discordbot.commands.MoveCommand;
import de.lecuutex.discordbot.commands.PlayCommand;
import de.lecuutex.discordbot.commands.RankCommand;
import de.lecuutex.discordbot.utils.Errors;
import lombok.Getter;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import okhttp3.internal.annotations.EverythingIsNonNull;

import java.util.ArrayList;

/**
 * A class created by yi.dnl - 06.02.2022 / 01:51
 */

@Getter
public class CommandManager extends ListenerAdapter {

    private final ArrayList<DefaultCommand> listener = new ArrayList<>();

    public CommandManager(CommandListUpdateAction commands) {
        registerCommands(commands);
        registerCommands();
    }

    public void registerCommands() {
        register(new ChatClearCommand());
        register(new RankCommand());
        register(new MoveCommand());
        register(new PlayCommand());
    }

    private void registerCommands(CommandListUpdateAction commands) {
        commands.addCommands(
                new CommandData("chatclear", "Lösche eine beliebige Anzahl an Nachrichten")
                        .addOption(OptionType.INTEGER, "amount", "Die Anzahl der Nachrichten, die gelöscht werden", true));

        commands.addCommands(new CommandData("rank", "Setze einen Rang")
                .addSubcommands(new SubcommandData("set", "Rang setzen")
                                .addOption(OptionType.USER, "user", "Nutzer angeben", true)
                                .addOption(OptionType.ROLE, "rank", "Rang angeben", true)
                        , new SubcommandData("remove", "Rang entfernen")
                                .addOption(OptionType.USER, "user", "Nutzer angeben", true)
                                .addOption(OptionType.ROLE, "rank", "Rang angeben", true)
                ));

        commands.addCommands(new CommandData("move", "Ziehe einen User in deinen VoiceChannel")
                .addOption(OptionType.USER, "user", "Nutzer angeben", true)
                .addOption(OptionType.CHANNEL, "channel", "VoiceChannel angeben", false));

        commands.addCommands(new CommandData("play", "Spiele Musik ab")
        .addOption(OptionType.STRING, "link", "Gib einen Link an", true));

        commands.queue();
    }

    @Override
    public void onSlashCommand(SlashCommandEvent event) {
        for (DefaultCommand command : listener) {
            if (!event.getName().equals(command.getCommand())) continue;
            command.setValues(event);

            if (!command.hasPermission(Permission.ADMINISTRATOR) && command.isAdminCommand()) {
                command.replyError(event, Errors.INSUFFICIENT_PERMISSIONS.getError());
                continue;
            }

            command.execute(event);

            if (!command.isReplied()) {
                event.reply("").complete().deleteOriginal().queue();
            }
        }
    }

    public void register(DefaultCommand instance) {
        listener.add(instance);
    }
}
package de.lecuutex.discordbot;

import de.lecuutex.discordbot.listener.MessageReceiveListener;
import de.lecuutex.discordbot.listener.UserJoinListener;
import de.lecuutex.discordbot.utils.MySQL;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A class created by yi.dnl - 02.02.2022 / 23:24
 */

public class DiscordBot {
    public static String token = "ODY2NjM3NjM1MTk0NzE2MTYx.YPVdMQ.jhxvazHzkqnf6OSjU5j6qHYQ6wo";

    public static void main(String[] args) {
        new MySQL();

        JDA jda = connect();
        changeActivity(jda);

        jda.addEventListener(new MessageReceiveListener());
        jda.addEventListener(new UserJoinListener());
    }

    public static JDA connect() {
        JDA jda = null;
        try {
            jda = JDABuilder.createDefault(token).
                    setAutoReconnect(true).
                    setStatus(OnlineStatus.ONLINE).build();
            jda.awaitReady();
        } catch (LoginException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return jda;
    }

    public static void changeActivity(JDA jda) {
        String[] strings = new String[]{"Alex", "ist", "ein", "blöder", "Wichser"};
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = () -> jda.getPresence().setActivity(Activity.watching(strings[new Random().nextInt(strings.length)]));
        scheduler.scheduleAtFixedRate(runnable, 0, 10, TimeUnit.SECONDS);
    }
}

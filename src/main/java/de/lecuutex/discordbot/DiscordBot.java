package de.lecuutex.discordbot;

import de.lecuutex.discordbot.listener.manager.ListenerManager;
import de.lecuutex.discordbot.utils.MySQL;
import de.lecuutex.discordbot.utils.Utils;
import de.lecuutex.discordbot.utils.embeds.Embed;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A class created by yi.dnl - 02.02.2022 / 23:24
 */

@Getter
@Setter
public class DiscordBot {

    @Getter
    private static final DiscordBot instance = new DiscordBot();

    private JDA jda;

    public static void main(String[] args) {
        JDA jda = instance.connect();
        instance.changeActivity(jda);
        instance.setJda(jda);

        new MySQL();
        new Embed(":pushpin: **|** Information", Utils.PREFIX + "`Zebra` hat sich erfolgreich hochgefahren!", Color.decode("#2ecc71")).send(Utils.LOG_CHANNEL);
    }

    private JDA connect() {
        JDA jda = null;
        try {
            jda = JDABuilder.createDefault(Utils.TOKEN, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_VOICE_STATES).
                    setMemberCachePolicy(MemberCachePolicy.ALL).
                    setAutoReconnect(true).
                    setStatus(OnlineStatus.ONLINE).build();
            ListenerManager.registerEvents(jda);
            jda.awaitReady();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }

        return jda;
    }

    public void changeActivity(JDA jda) {
        String[] strings = new String[]{"In progress...", "5%"};
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = () -> jda.getPresence().setActivity(Activity.playing(strings[new Random().nextInt(strings.length - 1)]));
        scheduler.scheduleAtFixedRate(runnable, 0, 60, TimeUnit.SECONDS);
    }

    /*
    WICHITG: Hiermit bekommst du alle Klassen eines Packages

     public static void init() {
        if (getPackets().size() > 0) {
            throw new RuntimeException("Loader already initialized.");
        }
        try {
            ClassPath.from(PacketLoader.class.getClassLoader()).getTopLevelClassesRecursive("de.leon.netty.packet.packets").forEach(v -> register((Class<? extends Packet>) v.load()));
            System.out.println("[PACKETS] Loaded " + getPackets().size() + " packets.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void register(Class<? extends Packet> packetClass) {
        getPackets().put(packetClass.getSimpleName().hashCode(), packetClass);
    }

    public static int getIdByClass(Class<? extends Packet> packetClass) {
        return getPackets().entrySet().stream().filter(e -> e.getValue().equals(packetClass)).map(Map.Entry::getKey).findFirst().orElse(0);
    }

    public static Class<? extends Packet> getClassById(int packetId) {
        return getPackets().get(packetId);
    }

    public static <T> T cast(Packet packet, Class<? extends Packet> packetClass) {
        return (T) packetClass.cast(packet);
    }
     */
}

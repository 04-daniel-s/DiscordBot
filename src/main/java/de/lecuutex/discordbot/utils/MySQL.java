package de.lecuutex.discordbot.utils;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A class created by yi.dnl - 03.02.2022 / 20:26
 */

@Getter
public class MySQL {
    private Connection connection;

    public MySQL() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://fdb34.awardspace.net:3306/4037089_discordbot?autoReconnect=true", "4037089_discordbot", "hwb45zHG5wh4%WHz4w5W");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

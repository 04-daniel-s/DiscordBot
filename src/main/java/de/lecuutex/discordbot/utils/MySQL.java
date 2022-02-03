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
    }
}

package de.lecuutex.discordbot.utils;

import com.mysql.cj.MysqlxSession;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Message;

import java.sql.*;

/**
 * A class created by yi.dnl - 03.02.2022 / 20:26
 */

@Getter
public class MySQL {
    private static Connection connection;

    public MySQL() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://167.235.20.117:3306/daniel?autoReconnect=true", "daniel", "6!_a(Mga.zHEy3(V");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        createTables();
    }

    private void createTables() {
        try {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS messages (id VARCHAR(36) PRIMARY KEY, name VARCHAR(36), link VARCHAR(120), message TEXT)").execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void storeMessage(Message message) {
        if (message.getMember().getUser().isBot()) return;

        String id = message.getId();
        String author = message.getAuthor().getAsTag();
        String content = message.getContentRaw();
        String link = message.getAuthor().getAvatarUrl();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO messages(id,name, message,link) VALUES (?,?,?,?) ON DUPLICATE KEY UPDATE message = ?");
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, content);
            preparedStatement.setString(4, link);
            preparedStatement.setString(5, content);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMessage(String id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM messages WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String id) {
        String message = "error";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                message = rs.getString("message");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public static String getAuthor(String id) {
        String author = "error";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                author = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public static String getLink(String id) {
        String link = "error";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM messages WHERE id = ?");
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                link = rs.getString("link");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return link;
    }
}

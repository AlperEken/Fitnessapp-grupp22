package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() {
        try {
            // Korrekt JDBC-URL utan dubbla snedstreck
            String url = "jdbc:postgresql://pgserver.mau.se/aq0647";
            String user = "aq0647"; // Byt ut med ditt användarnamn
            String password = "1pbrlr94"; // Byt ut med ditt lösenord

            // Ladda PostgreSQL JDBC-drivrutinen
            Class.forName("org.postgresql.Driver");

            // Skapa anslutning
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package model;

import util.DatabaseConnection;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class KontoDAO {

    // REGISTRERING MED HASHNING
    public boolean registreraKonto(Konto konto) {
        String sql = "INSERT INTO Konto (namn, efternamn, epost, lösenord, ålder, vikt, kön, dagligtMål) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Hasha lösenordet innan vi sparar det
            String hashedPassword = BCrypt.hashpw(konto.getLösenord(), BCrypt.gensalt());

            stmt.setString(1, konto.getNamn());
            stmt.setString(2, konto.getEfternamn());
            stmt.setString(3, konto.getEpost());
            stmt.setString(4, hashedPassword);
            stmt.setInt(5, konto.getÅlder());
            stmt.setDouble(6, konto.getVikt());
            stmt.setString(7, konto.getKön());
            stmt.setInt(8, konto.getDagligtMal());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // UPPDATERA VIKT
    public boolean uppdateraVikt(int kontoID, double nyVikt) {
        String sql = "UPDATE konto SET vikt = ? WHERE kontoID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, nyVikt);
            stmt.setInt(2, kontoID);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;  // Om minst en rad uppdaterades

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // TA BORT KONTO
    public boolean taBortKonto(int kontoID) {
        String sql = "DELETE FROM konto WHERE kontoID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kontoID);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;  // Om minst en rad raderades

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // KONTROLLERA OM E-POSTADRESSEN FINNS
    public boolean epostFinns(String epost) {
        String sql = "SELECT COUNT(*) FROM konto WHERE epost = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, epost);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;  // Om vi hittar en match, returnera true
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Om ingen match finns
    }

    public boolean valideraInloggning(String epost, String losenord) {
        String sql = "SELECT lösenord FROM konto WHERE epost = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, epost);  // Sätt e-posten i frågan
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String sparatHash = rs.getString("lösenord");  // Hämta det hashade lösenordet från databasen
                return BCrypt.checkpw(losenord, sparatHash);   // Jämför det angivna lösenordet med det lagrade hashade lösenordet
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Om e-post inte finns eller lösenordet inte matchar
    }


}

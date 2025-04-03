package controller;

import util.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;

public class KaloriController {

    public boolean läggTillKalorier(int kontoID, String matnamn, int kalorier) {
        String sql = "INSERT INTO Kalorier (datum, matnamn, kalorier, kontoID) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(LocalDate.now()));
            stmt.setString(2, matnamn);
            stmt.setInt(3, kalorier);
            stmt.setInt(4, kontoID);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int hämtaTotalKalorierIdag(int kontoID) {
        String sql = "SELECT SUM(kalorier) FROM Kalorier WHERE kontoID = ? AND datum = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kontoID);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1); // returnerar 0 om null
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int hämtaDagligtMal(int kontoID) {
        String sql = "SELECT dagligtMal FROM Konto WHERE kontoID = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kontoID);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int hämtaKalorierSenaste7Dagar(int kontoID) {
        String sql = "SELECT SUM(kalorier) FROM Kalorier WHERE kontoID = ? AND datum >= ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kontoID);
            stmt.setDate(2, Date.valueOf(LocalDate.now().minusDays(6)));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


}

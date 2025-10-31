/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nz.event.ticketbookingsystem.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import nz.event.ticketbookingsystem.config.Database;
import nz.event.ticketbookingsystem.model.Seat;
import nz.event.ticketbookingsystem.model.SeatStatus;

/**
 * This class handles database operations for Seat entities.
 * It retrieves seats by date and updates seat status in the SEATS table.
 * @author madhurima
 */
public class SeatDAO {

    public List<Seat> getSeatsByDate(String date) throws SQLException {
        List<Seat> seats = new ArrayList<>();
        String sql = "SELECT CODE, STATUS FROM SEATS WHERE EVENT_DATE = ? ORDER BY CODE";

        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, date);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    seats.add(new Seat(rs.getString("CODE"),
                            SeatStatus.valueOf(rs.getString("STATUS"))));
                }
            }
        }
        return seats;
    }

    public void updateStatus(String code, SeatStatus status) throws SQLException {
        try (Connection conn = Database.getConnection(); PreparedStatement ps = conn.prepareStatement("UPDATE SEATS SET STATUS=? WHERE CODE=?")) {

        ps.setString(1, status.name());
        ps.setString(2, code);
        ps.executeUpdate();
    }
}

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nz.event.ticketbookingsystem.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.*;

/**
 *
 * @author madhurima
 */
public class Database {

    private static final String URL = "jdbc:derby:/Users/madhurima/DerbyDB/ticketdb;create=true";
    private static boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            System.getLogger(Database.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }

        Connection conn = DriverManager.getConnection(URL);
        if (!initialized) {
            setupTables(conn);
            initialized = true;
        }
        return conn;
    }

    private static void setupTables(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement()) {
            st.executeUpdate("""
                CREATE TABLE SEATS(
                    CODE VARCHAR(10) PRIMARY KEY,
                    STATUS VARCHAR(20) NOT NULL
                )
            """);
        } catch (SQLException ignored) {
        }

        seedIfEmpty(conn);
    }

    private static void seedIfEmpty(Connection conn) throws SQLException {
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM SEATS")) {

            rs.next();
            if (rs.getInt(1) == 0) {
                char[] rows = {'A', 'B', 'C', 'D', 'E'};
                for (char row : rows) {
                    for (int i = 1; i <= 20; i++) {
                        st.executeUpdate("INSERT INTO SEATS VALUES('" + row + i + "', 'AVAILABLE')");
                    }
                }
                st.executeUpdate("UPDATE SEATS SET STATUS='RESERVED' WHERE CODE IN ('D9','D10','D11')");
            }
        }
    }
}

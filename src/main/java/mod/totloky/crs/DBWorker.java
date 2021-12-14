package mod.totloky.crs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DBWorker {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/db_table";
    private static final String user = "login";
    private static final String password = "password";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public static void dbOpenConnection() throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // opening database connection to MySQL server
        con = DriverManager.getConnection(url, user, password);

        // getting Statement object to execute query
        stmt = con.createStatement();

        System.out.println("Connection");
    }


    public static String getStat(String stat, String playerName) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String query = "SELECT `" + stat.toUpperCase(Locale.ROOT) + "` FROM `stats` WHERE Login = '" + playerName + "'";
        
        // executing SELECT query
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            int count = rs.getInt(1);
            return (String.valueOf(count));
        }

        return "Not in db";
    }
}

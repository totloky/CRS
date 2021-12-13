package mod.totloky.crs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DBWorker {
    // JDBC URL, username and password of MySQL server
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "db_name";
    private static final String password = "pass";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;


    public static String getStat(String stat, String playerName) throws SQLException {

        String query = "SELECT `" + stat.toUpperCase(Locale.ROOT) + "` FROM `stats` WHERE Login = '" + playerName + "'";


        // opening database connection to MySQL server
        con = DriverManager.getConnection(url, user, password);

        // getting Statement object to execute query
        stmt = con.createStatement();

        // executing SELECT query
        rs = stmt.executeQuery(query);

        while (rs.next()) {
            int count = rs.getInt(1);
            return (String.valueOf(count));
        }

        return "Характеристики нет в базе данных. Обратитесь к администратору.";
    }
}

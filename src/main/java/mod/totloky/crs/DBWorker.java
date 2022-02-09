package mod.totloky.crs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;

public class DBWorker {
    // JDBC URL, username and password of MySQL server
    static String[] params = ConfigManager.configRead();

    private static final String url = "jdbc:mysql://"+params[0]+"/"+params[1];
    private static final String user = params[2];
    private static final String password = params[3];

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;


    public static void dbOpenConnection() throws SQLException {

        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // opening database connection to MySQL server
            con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();

            System.out.println("[CRS] DB connected successfully");

        } catch (NullPointerException e) {
            System.out.println("[CRS] DB connection error");
        }
    }




    public static String getStat(String stat, String playerName) throws SQLException {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String query = "SELECT `" + stat.toUpperCase(Locale.ROOT) + "` FROM `stats` WHERE Login = '" + playerName + "'";


        // executing SELECT query
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int count = rs.getInt(1);
            return (String.valueOf(count));
        }

        return "Not in db";
    }
}

package mod.totloky.crs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;


public class MySQLHandler {
    // JDBC URL, database table, username and password for MySQL database obtained from config
    static String[] params = ConfigManager.configRead();

    private static final String url = "jdbc:mysql://"+params[0]+"/"+params[1];
    private static final String user = params[2];
    private static final String password = params[3];

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;



    // this method opening _new_ connection to MySQL database
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



    // this method creates a query to the database and receives from it the characteristic passed to it
    public static String getStat(String stat, String playerName) throws SQLException {

        String query = "SELECT `" + stat.toUpperCase(Locale.ROOT) + "` FROM `stats` WHERE Login = '" + playerName + "'";
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            int count = rs.getInt(1);
            return (String.valueOf(count));
        }
        return "Not in db";
    }

    // this method creates a query to the database and indicates the correct stat value for the player
    public static void setStat(String playerName, String stat, String points) throws SQLException {
        stmt.executeUpdate("UPDATE stats SET " + stat.toUpperCase(Locale.ROOT) + " = '" + Integer.parseInt(points) + "' WHERE Login = '" + playerName + "'");
    }


    // the following two methods are used to register a user without stats and with stats
    // without stats
    public static void regUser(String playerName, String password) throws SQLException {
        stmt.addBatch("INSERT INTO users VALUES(null, '" + playerName + "', '" + password + "', null)");
        stmt.executeBatch();
    }

    // with stats
    public static void regUser(String playerName, String password, String[] stats) throws SQLException {
        stmt.addBatch("INSERT INTO users VALUES(null, '" + playerName + "', '" + password + "', null)");
        stmt.addBatch("INSERT INTO stats VALUES('" + playerName + "', " + stats[0] + ", " + stats[1] + ", " + stats[2] + ", " + stats[3] + ", " + stats[4] + ", " + stats[5] + ")");
        stmt.executeBatch();
    }
}

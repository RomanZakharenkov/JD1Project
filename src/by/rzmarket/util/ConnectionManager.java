package by.rzmarket.util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionManager {

    public static final String URL = "db.url";
    public static final String USERNAME = "db.username";
    public static final String PASSWORD = "db.password";

    static {
        initDriver();
    }

    private static void initDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static Connection get() throws SQLException {
        return DriverManager.getConnection(PropertiesUtil.get(URL),
                PropertiesUtil.get(USERNAME), PropertiesUtil.get(PASSWORD));
    }

}

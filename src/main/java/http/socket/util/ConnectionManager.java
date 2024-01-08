package http.socket.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
@UtilityClass
public final class ConnectionManager {
    public static final String URL_KEY = "db.url";
    public static final String USERNAME_KEY = "db.user";
    public static final String PASSWORD_KEY = "db.password";
    public static final String DRIVER_KEY = "db.driver";
    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public static Connection get() {
        return DriverManager.getConnection(
            PropertiesUtil.get(URL_KEY),
            PropertiesUtil.get(USERNAME_KEY),
            PropertiesUtil.get(PASSWORD_KEY));

    }
}

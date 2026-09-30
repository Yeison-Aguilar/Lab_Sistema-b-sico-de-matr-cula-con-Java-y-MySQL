package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mysql://mysql.us.cloudlogin.co:3306/gamabasis_piig4?useSSL=false&serverTimezone=UTC";
    private static final String USER = "gamabasis_piig4";
    private static final String PASSWORD = "CK84Afq8k=";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
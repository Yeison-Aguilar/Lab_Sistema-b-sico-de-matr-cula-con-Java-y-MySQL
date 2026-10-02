package data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionBD {

    private static final Path CONFIG = Path.of("db.properties");

    public static Connection conectar() throws SQLException {
        Properties config = new Properties();
        try (InputStream in = Files.newInputStream(CONFIG)) {
            config.load(in);
        } catch (IOException e) {
            throw new SQLException("No se encontró db.properties "
                    + "(copie db.properties.example y complete los datos)", e);
        }
        
System.out.println("url es null? " + (config.getProperty("url") == null));
           return DriverManager.getConnection(
           config.getProperty("url"),
           config.getProperty("user"),
           config.getProperty("password"));
    }
}


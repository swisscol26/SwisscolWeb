package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/database_swisscol";
    private static final String USER = "root";
    private static final String PASSWORD = "Bonder981225.";

    public static Connection conectar() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conexion = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexión exitosa.");

            return conexion;

        } catch (Exception e) {

            e.printStackTrace();

            return null;
        }
    }
}
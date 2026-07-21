package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Clase encargada de establecer la conexión con la base de datos MySQL.
 * Proporciona un método para obtener un objeto Connection que será utilizado
 * por las clases DAO para realizar operaciones sobre la base de datos.
 *
 * Autor: Marlon Pulido
 */

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/database_swisscol";
    private static final String USER = "root";
    private static final String PASSWORD = "Bonder981225.";

    /**
 * Establece una conexión con la base de datos MySQL.
 *
 * @return objeto Connection si la conexión es exitosa;
 *         null en caso de que ocurra un error.
 */
    
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
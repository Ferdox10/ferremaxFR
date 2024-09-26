package ferreteriaferremax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FerreteriaFerremax {

    public static void main(String[] args) {
        // URL de la base de datos
        String url = "jdbc:mysql://localhost:3306/ferremax_db"; // Cambia 'ferremax_db' por el nombre de tu base de datos
        String user = "root"; // Usuario de la base de datos
        String password = ""; // Contraseña de la base de datos

        try {
            // Establecer la conexión
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos!");

            // Crear una sentencia
            Statement stmt = conn.createStatement();

            // Realizar una consulta para obtener todos los registros de la tabla 'usuarios'
            String sql = "SELECT * FROM usuarios"; // Consultar la tabla usuarios
            ResultSet rs = stmt.executeQuery(sql);

            // Procesar los resultados
            System.out.println("Usuarios disponibles:");
            while (rs.next()) {
                int id = rs.getInt("id");
                String email = rs.getString("email");
                String passwordDb = rs.getString("password");

                // Imprimir los resultados
                System.out.println("ID: " + id +
                                   ", Email: " + email +
                                   ", Contraseña: " + passwordDb);
            }

            // Cerrar ResultSet, Statement y conexión
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos o ejecutar la consulta");
            e.printStackTrace();
        }
    }
}

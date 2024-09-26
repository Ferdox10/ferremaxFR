package eliminarusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EliminarUsuarios {

    public static void main(String[] args) {
        // URL de la base de datos con SSL deshabilitado
        String url = "jdbc:mysql://localhost:3306/ferremax_db?useSSL=false"; // Asegúrate de que 'ferremax_db' es el nombre correcto de tu base de datos
        String user = "root";
        String password = ""; // Tu contraseña de MySQL aquí

        try {
            // Establecer la conexión con el driver de MySQL
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos!");

            // Crear una sentencia
            Statement stmt = conn.createStatement();

            // IDs de los usuarios a eliminar (incluye el usuario con ID 12 que mencionaste)
            int[] idsAEliminar = {15}; // Aquí coloca los IDs de los usuarios que deseas eliminar

            for (int id : idsAEliminar) {
                String sqlDelete = "DELETE FROM usuarios WHERE id = " + id;
                int rowsAffected = stmt.executeUpdate(sqlDelete);

                if (rowsAffected > 0) {
                    System.out.println("Usuario con ID " + id + " eliminado exitosamente.");
                } else {
                    System.out.println("Usuario con ID " + id + " no encontrado.");
                }
            }

            // Cerrar conexión
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos o ejecutar la consulta");
            e.printStackTrace();
        }
    }
}

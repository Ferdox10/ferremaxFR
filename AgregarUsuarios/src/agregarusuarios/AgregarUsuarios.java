package agregarusuarios;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AgregarUsuarios {

    public static void main(String[] args) {
        // URL de la base de datos con SSL deshabilitado
        String url = "jdbc:mysql://localhost:3306/ferremax_db?useSSL=false"; // Asegúrate de que 'ferremax_db' es el nombre correcto de tu base de datos
        String user = "root";
        String password = ""; // Tu contraseña de MySQL aquí

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese el email del nuevo usuario:");//Ingresar Email
        String nuevoEmail = scanner.nextLine();

        System.out.println("Ingrese el password del nuevo usuario:");//Ingresar Contraseña
        String nuevoPassword = scanner.nextLine();

        try {
            // Establecer la conexión con el driver de MySQL
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos!");

            // Preparar la sentencia SQL para insertar un nuevo usuario
            String sqlInsert = "INSERT INTO usuarios (email, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);

            // Asignar los valores
            pstmt.setString(1, nuevoEmail);
            pstmt.setString(2, nuevoPassword);

            // Ejecutar la inserción
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Nuevo usuario agregado exitosamente.");
            } else {
                System.out.println("Error al agregar el usuario.");
            }

            // Cerrar el PreparedStatement y la conexión
            pstmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos o ejecutar la consulta");
            e.printStackTrace();
        }
    }
}

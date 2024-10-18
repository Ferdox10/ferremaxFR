package com.mycompany.hibernateferremax;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class DatabaseConnection {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;

        try {
            // Configuración de Hibernate
            sessionFactory = new Configuration().configure("hibernate.cfg.xml")
                                                .addAnnotatedClass(Usuario.class)
                                                .buildSessionFactory();

            // Obtener una sesión
            Session session = sessionFactory.openSession();

            // Iniciar transacción
            session.beginTransaction();

            // Realizar la consulta para obtener todos los usuarios
            List<Usuario> usuarios = session.createQuery("from Usuario", Usuario.class).getResultList();

            // Procesar los resultados
            System.out.println("Usuarios disponibles:");
            for (Usuario usuario : usuarios) {
                System.out.println("ID: " + usuario.getId() +
                                   ", Email: " + usuario.getEmail() +
                                   ", Contraseña: " + usuario.getPassword());
            }

            // Confirmar la transacción
            session.getTransaction().commit();

        } catch (Exception e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        } finally {
            if (sessionFactory != null) {
                sessionFactory.close();
            }
        }
    }
}

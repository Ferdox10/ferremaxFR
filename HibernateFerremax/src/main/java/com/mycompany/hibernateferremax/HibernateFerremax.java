package com.mycompany.hibernateferremax;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class HibernateFerremax {

    private static SessionFactory factory;

    public static void main(String[] args) {
        try {
            // Configuración de Hibernate con la configuración actualizada
            factory = new Configuration().configure().buildSessionFactory();
            ProductoService productoService = new ProductoService(factory);

            // Crear un nuevo producto
            Producto nuevoProducto = new Producto("Martillo", 15.99, 30);
            productoService.agregarProducto(nuevoProducto);

            // Listar productos
            List<Producto> productos = productoService.obtenerProductos();
            for (Producto producto : productos) {
                System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() +
                                   ", Precio: " + producto.getPrecio() + ", Cantidad: " + producto.getCantidad());
            }

            // Actualizar un producto
            nuevoProducto.setPrecio(18.99);
            productoService.actualizarProducto(nuevoProducto);

            // Eliminar un producto
            productoService.eliminarProducto(nuevoProducto.getId());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (factory != null) {
                factory.close();
            }
        }
    }
}

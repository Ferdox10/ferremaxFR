package com.mycompany.hibernateferremax;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static EntityManagerFactory entityManagerFactory;

    public static void main(String[] args) {
        // Inicializar la EntityManagerFactory
        entityManagerFactory = Persistence.createEntityManagerFactory("FerreMaxPU");
        ProductoDAO productoDAO = new ProductoDAO(entityManagerFactory);
        Scanner scanner = new Scanner(System.in);

        // Menú para ingresar productos
        while (true) {
            System.out.println("Ingrese los detalles del producto o escriba 'salir' para terminar:");
            
            // Solicitar nombre del producto
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            if (nombre.equalsIgnoreCase("salir")) break;
            
            // Solicitar descripción
            System.out.print("Descripción: ");
            String descripcion = scanner.nextLine();
            
            // Solicitar precio unitario
            System.out.print("Precio Unitario: ");
            double precioUnitario = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer

            // Solicitar marca
            System.out.print("Marca: ");
            String marca = scanner.nextLine();
            
            // Solicitar código de barras
            System.out.print("Código de Barras: ");
            String codigoBarras = scanner.nextLine();
            
            // Solicitar ID de categoría
            System.out.print("ID Categoría: ");
            Integer idCategoria = scanner.nextInt();
            
            // Solicitar ID
            System.out.print("ID: ");
            Long id = scanner.nextLong();
            
            // Solicitar cantidad
            System.out.print("Cantidad: ");
            int cantidad = scanner.nextInt();
            
            // Solicitar precio
            System.out.print("Precio: ");
            double precio = scanner.nextDouble();
            scanner.nextLine(); // Limpiar el buffer
            
            // Crear producto en la base de datos
            productoDAO.crearProducto(nombre, descripcion, precioUnitario, marca, codigoBarras, idCategoria, id, cantidad, precio);

            // Listar productos
            List<Producto> productos = productoDAO.listarProductos();
            for (Producto producto : productos) {
                System.out.println(producto.getIdProducto() + ": " + producto.getNombre() + " - Precio: " + producto.getPrecio() + " - Cantidad: " + producto.getCantidad());
            }
        }

        // Cerrar la conexión
        productoDAO.cerrar();
        entityManagerFactory.close();
        scanner.close(); // Cerrar el Scanner
    }
}

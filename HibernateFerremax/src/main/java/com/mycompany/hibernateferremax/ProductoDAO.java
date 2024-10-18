package com.mycompany.hibernateferremax;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;

public class ProductoDAO {
    private EntityManagerFactory entityManagerFactory;

    // Constructor para inicializar el EntityManagerFactory
    public ProductoDAO(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    // Método para crear un nuevo producto
    public void crearProducto(String nombre, String descripcion, double precioUnitario, String marca, String codigoBarras, Integer idCategoria, Long id, int cantidad, double precio) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Producto producto = new Producto(nombre, descripcion, precioUnitario, marca, codigoBarras, idCategoria, id, cantidad, precio);
            em.persist(producto); // Guardar el producto en la base de datos
            
            tx.commit();
            System.out.println("Producto creado exitosamente.");
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Error al crear el producto: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de cerrar el EntityManager
            }
        }
    }

    // Método para actualizar un producto existente
    public void actualizarProducto(Long idProducto, String nuevoNombre, double nuevoPrecio, int nuevaCantidad) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Producto producto = em.find(Producto.class, idProducto); // Buscar producto por ID
            if (producto != null) {
                producto.setNombre(nuevoNombre);
                producto.setPrecio(nuevoPrecio);
                producto.setCantidad(nuevaCantidad);
                em.merge(producto); // Actualizar el producto
                
                tx.commit();
                System.out.println("Producto actualizado exitosamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Error al actualizar el producto: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de cerrar el EntityManager
            }
        }
    }

    // Método para eliminar un producto existente
    public void eliminarProducto(Long idProducto) {
        EntityManager em = null;
        EntityTransaction tx = null;
        try {
            em = entityManagerFactory.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            Producto producto = em.find(Producto.class, idProducto); // Buscar producto por ID
            if (producto != null) {
                em.remove(producto); // Eliminar el producto
                
                tx.commit();
                System.out.println("Producto eliminado exitosamente.");
            } else {
                System.out.println("Producto no encontrado.");
            }
        } catch (Exception e) {
            if (tx != null && tx.isActive()) tx.rollback();
            System.out.println("Error al eliminar el producto: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de cerrar el EntityManager
            }
        }
    }

    // Método para listar todos los productos
    public List<Producto> listarProductos() {
        EntityManager em = null;
        List<Producto> productos = null;
        try {
            em = entityManagerFactory.createEntityManager();
            productos = em.createQuery("SELECT p FROM Producto p", Producto.class).getResultList();
        } catch (Exception e) {
            System.out.println("Error al listar productos: " + e.getMessage());
        } finally {
            if (em != null && em.isOpen()) {
                em.close(); // Asegúrate de cerrar el EntityManager
            }
        }
        return productos;
    }

    // Método para cerrar el EntityManagerFactory
    public void cerrar() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

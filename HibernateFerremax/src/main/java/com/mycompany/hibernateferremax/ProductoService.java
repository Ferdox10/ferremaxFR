package com.mycompany.hibernateferremax;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

public class ProductoService {

    private SessionFactory sessionFactory;

    // Constructor que recibe el SessionFactory
    public ProductoService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void agregarProducto(Producto producto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(producto);
            transaction.commit();
            System.out.println("Producto agregado: " + producto.getNombre());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Producto> obtenerProductos() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Producto", Producto.class).list();
        }
    }

    public void actualizarProducto(Producto producto) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(producto);
            transaction.commit();
            System.out.println("Producto actualizado: " + producto.getNombre());
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void eliminarProducto(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Producto producto = session.get(Producto.class, id);
            if (producto != null) {
                session.delete(producto);
                System.out.println("Producto eliminado: " + producto.getNombre());
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}

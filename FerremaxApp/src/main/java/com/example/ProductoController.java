package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = productoService.getAllProductos();
        model.addAttribute("productos", productos);
        return "listar_productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProducto(Model model) {
        model.addAttribute("producto", new Producto());
        return "nuevo_producto";
    }

    @PostMapping
    public String crearProducto(@ModelAttribute Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable int id, Model model) {
        Producto producto = productoService.getProductoById(id);
        if (producto == null) {
            return "redirect:/error";
        }
        model.addAttribute("producto", producto);
        return "editar_producto";
    }

    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable int id, @ModelAttribute Producto producto) {
        producto.setIdProducto(id);
        productoService.saveProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable int id) {
        productoService.deleteProducto(id);
        return "redirect:/productos";
    }
}

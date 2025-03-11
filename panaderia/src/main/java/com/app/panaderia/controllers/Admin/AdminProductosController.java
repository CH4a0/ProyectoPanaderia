package com.app.panaderia.controllers.admin;

import com.app.panaderia.modelo.entidades.Producto;
import com.app.panaderia.modelo.entidades.Categoria;
import com.app.panaderia.modelo.servicios.ProductoService;
import com.app.panaderia.modelo.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminProductosController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    // Ruta para ver productos
    @GetMapping("/admin/productos")
    public String productos() {
        System.out.println("Admin productos");
        return "/admin/productos/index";  // Ruta a la vista de productos
    }

    // Ruta para crear un nuevo producto
    @GetMapping("/admin/productos/new")
    public String newProducto(Model model) {
        System.out.println("Admin productos New");

        // Crear un objeto Producto vacío
        Producto producto = new Producto();

        // Obtener la lista de categorías
        List<Categoria> categorias = categoriaService.getAll();

        // Agregar el objeto Producto y las categorías al modelo
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categorias);

        return "/admin/productos/new";  // Ruta a la vista para crear un nuevo producto
    }

    // Ruta para guardar el nuevo producto
    @PostMapping("/admin/productos/new")
    public String createProducto(@ModelAttribute Producto producto) {
        // Guardar el producto
        productoService.create(producto);
        return "redirect:/admin/productos";  // Redirigir al listado de productos
    }
}

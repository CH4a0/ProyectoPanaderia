package com.app.panaderia.controllers.admin;

import com.app.panaderia.modelo.entidades.Producto;
import com.app.panaderia.modelo.entidades.Categoria;
import com.app.panaderia.modelo.servicios.AdminProductoService;
import com.app.panaderia.modelo.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductosController {

    @Autowired
    private AdminProductoService adminProductoService;

    @Autowired
    private CategoriaService categoriaService;

    // Ruta para ver todos los productos en la sección de administración
    @GetMapping
    public String listarProductos(Model model) {
        List<Producto> productos = adminProductoService.getAll();
        List<Categoria> categorias = categoriaService.getAll(); // Obtener categorías
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias); // Pasar categorías a la vista
        return "admin/productos/index";
    }

    // Ruta para ver productos por categoría en la sección de administración
    @GetMapping("/categoria/{tipo}")
    public String listarProductosPorCategoria(@PathVariable("tipo") String tipo, Model model) {
        List<Producto> productos = adminProductoService.getByCategoria(tipo); // Este servicio debe devolver los
                                                                              // productos según la categoría
        model.addAttribute("productos", productos);
        model.addAttribute("categoria", tipo);

        // Pasar la lista de categorías también
        List<Categoria> categorias = categoriaService.getAll();
        model.addAttribute("categorias", categorias);

        return "admin/productos/index"; // Asegúrate de que esta ruta coincida con la ubicación de tu plantilla
    }

    @GetMapping("/productos/ordenarPorCategoria")
    public String ordenarPorCategoria(String categoria, Model model) {
        List<Producto> productos = adminProductoService.getByCategoria(categoria);
        model.addAttribute("productos", productos);
        return "productos/lista";  // Aquí reemplaza con la vista correcta que muestra los productos
    }

    // Ruta para mostrar el formulario de nuevo producto
    @GetMapping("/new")
    public String mostrarFormularioNuevoProducto(Model model) {
        List<Categoria> categorias = categoriaService.getAll();
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categorias);
        return "admin/productos/new"; // Asegúrate de que esta ruta coincida con la ubicación de tu plantilla
    }

    @PostMapping("/new")
    public String guardarNuevoProducto(Producto producto,
            @RequestParam(value = "imagen", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Obtener la categoría del producto
                String categoria = producto.getCategoria().getTipo().toLowerCase();
    
                // Definir la ruta de almacenamiento basada en la categoría
                String rutaBase = "src/main/resources/static/img/productos/";
                String rutaCategoria = switch (categoria) {
                    case "dulce" -> "dulces/";
                    case "salado" -> "salado/";
                    case "mixto" -> "mixto/";
                    case "pan" -> "pan/";
                    default -> "otros/";
                };
    
                // Construir la ruta completa para el almacenamiento
                String rutaCompleta = rutaBase + rutaCategoria + file.getOriginalFilename();
                Path path = Paths.get(rutaCompleta);
    
                // Guardar el archivo en el sistema de archivos
                Files.write(path, file.getBytes());
    
                // Guardar la ruta relativa en la base de datos
                producto.setImagen("/img/productos/" + rutaCategoria + file.getOriginalFilename());
    
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el error de escritura del archivo
                producto.setImagen("/img/default.jpg"); // Imagen por defecto en caso de error
            }
        } else {
            // Si no hay imagen, asignar una imagen por defecto
            producto.setImagen("/img/default.jpg");
        }
    
        // Guardar el producto en la base de datos
        adminProductoService.create(producto);
        return "redirect:/admin/productos";
    }
}        

package com.app.panaderia.controllers.admin;

import com.app.panaderia.modelo.entidades.Producto;
import com.app.panaderia.modelo.entidades.Categoria;
import com.app.panaderia.modelo.servicios.AdminProductoService;
import com.app.panaderia.modelo.servicios.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String listarProductos(@RequestParam(required = false) String categoria, Model model) {
        List<Producto> productos;
        if (categoria != null && !categoria.isEmpty()) {
            productos = adminProductoService.getByCategoria(categoria);
            model.addAttribute("categoriaSeleccionada", categoria); // Mantener la categoría seleccionada
        } else {
            productos = adminProductoService.getAll();
        }

        List<Categoria> categorias = categoriaService.getAll();
        model.addAttribute("productos", productos);
        model.addAttribute("categorias", categorias);

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
        return "productos/lista"; // Aquí reemplaza con la vista correcta que muestra los productos
    }

    // Ruta para mostrar el formulario de nuevo producto
    @GetMapping("/new")
    public String mostrarFormularioNuevoProducto(Model model) {
        List<Categoria> categorias = categoriaService.getAll();
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categorias);
        return "admin/productos/new"; // Asegúrate de que esta ruta coincida con la ubicación de tu plantilla
    }

    // Ruta para procesar el formulario de nuevo producto
    @PostMapping("/new")
    public String guardarNuevoProducto(Producto producto,
            @RequestParam(value = "imagen", required = false) MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                // Guardar el archivo en el sistema de archivos
                Path path = Paths.get("src/main/resources/static/images/" + file.getOriginalFilename());
                Files.write(path, file.getBytes());
                producto.setImagen("/images/" + file.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el error de escritura del archivo
            }
        } else {
            // Si no se proporciona una imagen, puedes establecer una imagen por defecto o
            // dejar el campo vacío
            producto.setImagen("/img/default.jpg"); // Ruta a una imagen por defecto
        }
        adminProductoService.create(producto);
        return "redirect:/admin/productos"; // Redirigir al listado de productos después de guardar
    }
}

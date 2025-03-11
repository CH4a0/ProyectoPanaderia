package com.app.panaderia.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.app.panaderia.modelo.servicios.ProductoService;
import com.app.panaderia.modelo.entidades.Producto;
import org.springframework.ui.Model;

@Controller
public class ProductosController {
    @Autowired 
    private ProductoService pser;

    @GetMapping("/productos")
    public String getproducto(Model modelo) {
        System.out.println();
        System.out.println();
        System.out.println();
        List<Producto> productos = pser.getAll();
        productos.forEach(System.out::println);
        modelo.addAttribute("Lproductos", productos);
        return "www/productos/listar";
    }
}

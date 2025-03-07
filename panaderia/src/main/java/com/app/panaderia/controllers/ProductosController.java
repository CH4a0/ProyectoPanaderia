package com.app.panaderia.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductosController {
    
    @GetMapping("/productos")
    public String index() {
        return "www/productos/index";
    }
}

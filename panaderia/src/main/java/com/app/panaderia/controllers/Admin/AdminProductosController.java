package com.app.panaderia.controllers.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class AdminProductosController {
    @GetMapping("/admin/productos")
    public String productos() {
        System.out.println("Admin productos");
        return "/admin/productos/index";
    }

    @GetMapping("/admin/productos/new")
    public String newproductos() {
        System.out.println("Admin productos New");
        return "/admin/productos/new";
    }
}

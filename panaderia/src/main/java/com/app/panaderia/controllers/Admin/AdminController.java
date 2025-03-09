package com.app.panaderia.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {
    
    @GetMapping("/admin")
    public String admin() {
        System.out.println("admin");
        return "admin/index";  // Sin la barra inicial
    }
    

}

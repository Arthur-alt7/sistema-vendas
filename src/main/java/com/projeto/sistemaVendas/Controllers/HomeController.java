package com.projeto.sistemaVendas.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/administrativo")
    public String acessarHome(){
        return "administrativo/home";
    }
}

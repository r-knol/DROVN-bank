package nl.hva.miw.internetbanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    
    @RequestMapping("/")
    public String homeGetHandler(Model model) {
        return "pages/home";
    }
    
}

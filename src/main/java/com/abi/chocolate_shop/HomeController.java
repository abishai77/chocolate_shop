package com.abi.chocolate_shop;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


    @Controller
    public class HomeController{
        private final ChocolateRepository chocolateRepository;
        public HomeController(ChocolateRepository chocolateRepository){
            this.chocolateRepository = chocolateRepository;
        }
        @GetMapping("/")
        public String home(Model model){
            model.addAttribute("chocolates", chocolateRepository.findAll());
            return "home";
        }
    }


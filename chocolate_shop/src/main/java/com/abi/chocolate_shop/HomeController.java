package com.abi.chocolate_shop;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController{
    private final ChocolateRepository chocolateRepository;
        public HomeController(ChocolateRepository chocolateRepository){
            this.chocolateRepository = chocolateRepository;
        }
    @GetMapping("/")
    public String home(@RequestParam(required = false) String keyword, Model model) {

        List<Chocolate> chocolates;

        if (keyword != null && !keyword.trim().isEmpty()) {
            chocolates = chocolateRepository
                    .findByNameContainingIgnoreCase(keyword);
        } else {
            chocolates = chocolateRepository.findAll();
        }

        model.addAttribute("chocolates", chocolates);

        return "home";
    }
       @GetMapping("/add-to-cart/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session){
            List<Chocolate> cart =  (List<Chocolate>) session.getAttribute("cart");
            if(cart == null){
                cart = new ArrayList<>();
            }
           chocolateRepository.findById(id).ifPresent(cart::add);
            session.setAttribute("cart",cart);
            return "redirect:/";
       }
       @GetMapping("/cart")
    public String cart(HttpSession session, Model model){
            List<Chocolate> cart =  (List<Chocolate>) session.getAttribute("cart");
            double total = 0;
            if(cart == null) {
                cart = new ArrayList<>();
            }
               for (Chocolate c: cart){
                   total += c.getPrice();
               }
               model.addAttribute("cart",cart);
            model.addAttribute("total",total);
            return "cart";
       }
       @GetMapping("/remove")
       public String removeFromCart(@RequestParam("name")String name, HttpSession session){
            List<Chocolate> cart =  (List<Chocolate>) session.getAttribute("cart");
            if(cart != null){
                cart.removeIf(item -> item.getName().equals(name));
                session.setAttribute("cart",cart);
            }
            return "redirect:/cart";
       }
       @GetMapping("/checkout")
    public String checkout(HttpSession session){
            List<Chocolate> cart =  (List<Chocolate>) session.getAttribute("cart");
            if(cart == null || cart.isEmpty()){
                return "redirect:/cart";
            }
            return "checkout";
       }
       @PostMapping("/confirm-order")
    public String confirmOrder(@RequestParam String name,
                               @RequestParam String address,@RequestParam String phone,
                               HttpSession session, Model model){
            List<Chocolate> cart =  (List<Chocolate>) session.getAttribute("cart");
            if(cart != null){
                for(Chocolate c: cart) {
                    Chocolate cdb = chocolateRepository.findById((long) c.getId()).orElse(null);
                    if(cdb != null && cdb.getQuantity()>0){
                        cdb.setQuantity(cdb.getQuantity()-1);
                        chocolateRepository.save(cdb);
                    }
                    else {
                        model.addAttribute("error",cdb.getName()+"OUT OF STOCK");
                    }
                }
            }
            session.removeAttribute("cart");
            model.addAttribute("customer",name);
            return "success";
       }
    }


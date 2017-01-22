package com.holeksa.controller;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.repository.CoffeeProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by bourbonkid on 19.01.17.
 */
@Controller
@RequestMapping("/csrf")
public class CsrfController {

    @Autowired
    CoffeeProductRepository coffeeProductRepository;


    @GetMapping()
    public String menu() {
        return "csrfAttackVector";
    }

    @GetMapping("/coffeeList")
    public String greetings(Model model) {
        model.addAttribute("coffeeProducts", coffeeProductRepository.findAll());
        return "list";
    }

    @GetMapping("/addCoffee")
    public String addCoffeeFormGet(Model model) {
        model.addAttribute("coffeeProduct", new CoffeeProduct());
        return "addCoffeeGet";
    }

    @GetMapping("/addCoffeeGet")
    public String addCoffeeSubmitGet(@ModelAttribute CoffeeProduct coffeeProduct) {
        coffeeProductRepository.save(coffeeProduct);
        return "result";
    }

    @GetMapping("/addCoffeePost")
    public String addCoffeeFormPost(Model model) {
        model.addAttribute("coffeeProduct", new CoffeeProduct());
        return "addCoffeePost";
    }

    @PostMapping("/addCoffeePost")
    public String addCoffeeSubmitPost(@ModelAttribute CoffeeProduct coffeeProduct) {
        coffeeProductRepository.save(coffeeProduct);
        return "result";
    }

}

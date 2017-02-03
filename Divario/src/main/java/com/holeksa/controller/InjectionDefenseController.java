package com.holeksa.controller;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.repository.CoffeeProductRepository;
import com.mysql.jdbc.PreparedStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by bourbonkid on 27.01.17.
 */
@Controller
@RequestMapping("/injection-defense")
public class InjectionDefenseController {

    @Autowired
    CoffeeProductRepository coffeeProductRepository;

    @GetMapping
    String menu() {
        return "InjectionAttackVector";
    }

    @GetMapping("/searchCoffee")
    String searchForm(Model model) {
        return "searchCoffee";
    }

    @GetMapping(value = "/search")
    String searchCoffee(@RequestParam(value = "q") String searchString, Model model) {

        model.addAttribute("coffees", coffeeProductRepository.findCustomQuery(searchString));
        model.addAttribute("paramsQ", searchString);

        return "searchResult";

    }

}

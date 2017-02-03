package com.holeksa.controller;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.model.Comment;
import com.holeksa.repository.CoffeeProductRepository;
import com.holeksa.repository.CommentRepository;
import com.holeksa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


/**
 * Created by bourbonkid on 27.01.17.
 */
@Controller
@RequestMapping("/injection")
public class InjectionController {

    @Autowired
    CoffeeProductRepository coffeeProductRepository;

    @PersistenceContext
    EntityManager em;

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

        Query searchQuery = em.createNativeQuery("SELECT * FROM coffee_product cp WHERE cp.name = '" + searchString + "';", CoffeeProduct.class);
        List<CoffeeProduct> coffeeProductList = searchQuery.getResultList();
        model.addAttribute("coffees", coffeeProductList);
        model.addAttribute("paramsQ", searchString);

        return "searchResult";

    }

}

package com.holeksa.controller;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.model.Comment;
import com.holeksa.repository.CoffeeProductRepository;
import com.holeksa.repository.CommentRepository;
import com.holeksa.repository.UserRepository;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Created by bourbonkid on 27.01.17.
 */
@Controller
@RequestMapping("/xss-esapi")
public class XssEsapiDefenseController {

    @Autowired
    CoffeeProductRepository coffeeProductRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @GetMapping
    String menu() {
        return "xssAttackVector";
    }

    @GetMapping("/searchCoffee")
    String searchForm(Model model) {
        return "searchCoffee";
    }

    @GetMapping(value = "/search")
    String searchCoffee(@RequestParam(value = "q") String searchString, Model model) {

        //This value will return to HTML element, so encodeForHTML is valid encoder
        String safeSearchString = ESAPI.encoder().encodeForHTML(searchString);

        //List of CoffeeProduct will return to HTML element, so encoderForHTML is valid encoder
        List<CoffeeProduct> coffeeProductList = new ArrayList<>();
        for (CoffeeProduct prod : coffeeProductRepository.findByName(safeSearchString)) {
            CoffeeProduct encodeProduct = new CoffeeProduct();
            encodeProduct.setName(ESAPI.encoder().encodeForHTML(prod.getName()));
            encodeProduct.setDescription(ESAPI.encoder().encodeForHTML(prod.getDescription()));
            encodeProduct.setRate(prod.getRate());
            encodeProduct.setPrice(prod.getPrice());
        }
        model.addAttribute("coffees", coffeeProductList);
        model.addAttribute("paramsQ", safeSearchString);

        return "searchResult";

    }

    @GetMapping("/comments")
    String getComments(Model model) {

        List<Comment> commentList = new ArrayList<>();
        for (Comment comment : commentRepository.findAll()){
            Comment safeComment = new Comment();
            safeComment.setTitle(ESAPI.encoder().encodeForHTML(comment.getTitle()));
            safeComment.setDescription(ESAPI.encoder().encodeForHTML(comment.getDescription()));
           commentList.add(safeComment);
        }

        model.addAttribute("comments", commentList);
        return "commentList";
    }

    @GetMapping("/addComment")
    String addCommentForm(Model model) {
        model.addAttribute("comment", new Comment());

        return "addComment";
    }

    @PostMapping("/addComment")
    String addCommentPost(@ModelAttribute Comment comment) {

        commentRepository.save(comment);

        return "xssAttackVector";
    }


}

package com.holeksa.controller;

import com.holeksa.model.Comment;
import com.holeksa.repository.CoffeeProductRepository;
import com.holeksa.repository.CommentRepository;
import com.holeksa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * Created by bourbonkid on 27.01.17.
 */
@Controller
@RequestMapping("/xss-csp")
public class XssCspDefenseController {

    @Autowired
    CoffeeProductRepository coffeeProductRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

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

        model.addAttribute("coffees", coffeeProductRepository.findByName(searchString));
        model.addAttribute("paramsQ", searchString);

        return "searchResult";

    }

    @GetMapping("/comments")
    String getComments(Model model) {
        model.addAttribute("comments", commentRepository.findAll());
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

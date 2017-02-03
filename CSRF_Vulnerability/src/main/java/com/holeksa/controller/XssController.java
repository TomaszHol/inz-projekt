package com.holeksa.controller;

import com.holeksa.model.CoffeeProduct;
import com.holeksa.model.Comment;
import com.holeksa.repository.CoffeeProductRepository;
import com.holeksa.repository.CommentRepository;
import com.holeksa.repository.UserRepository;
import com.mysql.jdbc.integration.jboss.ExtendedMysqlExceptionSorter;
import org.owasp.esapi.ESAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


/**
 * Created by bourbonkid on 27.01.17.
 */
@Controller
@RequestMapping("/xss")
public class XssController {

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

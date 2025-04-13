package com.batch2.artifact1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.batch2.artifact1.domain.Login;
import com.batch2.artifact1.service.LoginService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private LoginService service;

    // Show login page on root or "/login"
    @GetMapping({"/", "/login"})
    public String showLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password");
        }
        return "login"; // login.html
    }

    // Home page after login
    @GetMapping("/home")
    public String homePage() {
        return "home"; // home.html (Lattefy dashboard)
    }

    // Your Orders page
    @GetMapping("/urorder")
    public String orderPage() {
        return "urorderrec"; // urorderrec.html
    }

    // Favorites page
    @GetMapping("/favorites")
    public String favoritesPage() {
        return "favorites"; // favorites.html
    }

    // Drink Menu (default landing on Coffee tab)
    @GetMapping("/drink-menu-coffee")
    public String drinkMenuCoffeePage() {
        return "drink-menu-coffee"; // drink-menu-coffee.html
    }

    // Drink Menu - Chocolate category
    @GetMapping("/drink-menu-choco")
    public String drinkMenuChocoPage() {
        return "drink-menu-choco"; // drink-menu-choco.html
    }

    // Drink Menu - Others category
    @GetMapping("/drink-menu-others")
    public String drinkMenuOthersPage() {
        return "drink-menu-others"; // drink-menu-others.html
    }

    @GetMapping("/urpastorder")
    public String urPastOrderPage() {
        return "urpastorder"; 
    }

    // Handle login form POST
    @PostMapping("/login")
    public String processLogin(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        Login user = service.log(username, password);

        if (user != null) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login"; // login.html with error
        }
    }
}

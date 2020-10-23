package com.fabriciojf.english.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Fabricio S Costa fabriciojf@gmail.com
 * @class MainController
 * @version 1.0
 * @since 23/10/2020
 */
@Controller
public class MainController {

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "list-words.html";
    }
    
    // Login form
    @RequestMapping("/login-processing")
    public String loginProcessing() {
        return "login-processing.html";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
}

package com.tts.ecomspring.controller;

import com.tts.ecomspring.model.User;
import com.tts.ecomspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AuthenticationController {
    @Autowired
    private UserService userService;

    @GetMapping(value="/login")
    public String login(){

        return "login";
    }

    @PostMapping(value =  "/signin")
    public String signin(@Valid User user,
                         @RequestParam String submit,
                         BindingResult bindingResult,
                         HttpServletRequest request) throws ServletException {
        String password = user.getPassword();
        if (submit.equals("up")) {
            if (userService.findByUsername(user.getUsername()) == null) {
                userService.saveNew(user);
            } else{
                bindingResult.rejectValue("username", "error.user", "User is already taken.");
                return "signin";
            }
        }
        request.login(user.getUsername(), password);
        return "redirect:/";
    }
}

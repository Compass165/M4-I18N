package com.logini18n.controller;

import com.logini18n.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;
import java.util.ResourceBundle;

@Controller
public class AuthController {
//    @Autowired
//    private MessageSource messageSource;
    @GetMapping("/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("user", new User());
//        String hello = messageSource.getMessage("hello", null, Locale.ENGLISH);
//        String hello = messageSource.getMessage("hello", null, new Locale("vi"));

        ResourceBundle rb = ResourceBundle.getBundle("message", Locale.JAPAN);
        String hello = rb.getString("hello");
        modelAndView.addObject("hello", hello);
        return modelAndView;
    }

    @PostMapping("/doLogin")
    public ModelAndView doLogin(@ModelAttribute User user) {
        if(user.getUsername().equals("admin") && user.getPassword().equals("123456")){
            ModelAndView modelAndView = new ModelAndView("/success");
            modelAndView.addObject("user",user);
            return modelAndView;
        }
        return new ModelAndView("/error");
    }
}
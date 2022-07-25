package com.example.thymeleafusers.controllers;

import com.example.thymeleafusers.models.Users;
import com.example.thymeleafusers.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserRepository repository;

    @GetMapping({"/user", ""})
    public Object getAllUser() {
        ModelAndView mv = new ModelAndView("user");
        mv.addObject("infoUser", repository.findAll());
        return mv;
    }

    @GetMapping("/addUser")
    public Object addNewUser() {
        ModelAndView mv = new ModelAndView("add-user");
        Users user = new Users();
        mv.addObject("newUser", user);
        return mv;
    }

    @PostMapping("/saveUser")
    public Object saveUser(@ModelAttribute Users newUser) {
        repository.save(newUser);
        return "redirect:/user";
    }

    @GetMapping("/deleteUser")
    public Object deleteUser(@RequestParam Integer id) {
        repository.deleteById(id);
        return "redirect:/user";
    }

    @GetMapping("/updateUser")
    public Object updateUser(@RequestParam Integer id) {
        ModelAndView mv = new ModelAndView("add-user");
        Users user = repository.findById(id).get();
        mv.addObject("newUser", user);
        return mv;
    }
}

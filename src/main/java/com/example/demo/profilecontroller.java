/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author bibash
 */
@Controller
public class profilecontroller {
    
    @Autowired
    private UserService userservice;
    
    @Autowired
    private HistoryService service;
    
    @RequestMapping("/profile")
    public String ProfilePage(Model model, Principal principal){
        
        String username = principal.getName();
        
        User user = userservice.findByusername(username);
        
        model.addAttribute("history",service.findUserTask(user));
        
        return "profile";
    }
    
}

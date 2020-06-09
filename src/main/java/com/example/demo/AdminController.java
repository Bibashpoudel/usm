/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author bibash
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    
    @Autowired    
    private UserService service;
    
    
    @GetMapping("")
    public String home(){
        return "admin/dashboard";
    
    
    }
    
    @GetMapping("/listUser")
    public String UserList(Model model, HttpServletRequest request, String username){
        
       
         List<User> listUser = service.ListUser();
        
        
        
        
        
        model.addAttribute ("listUser", listUser);
        
//        try{
//            User user = service.findByusername(username);
//
//
//            if(user.isActive() == true){
//
//                request.setAttribute("message" ,"block");
//                
//
//            }
//            else{
//                request.setAttribute("message" ,"Active");
//            }
       
        
        
//        }catch(Exception e){
//            System.out.println("e");
//        }
        
        
        
       
        return "admin/userlist";
    
    }
    @RequestMapping("/register")
    public String Registeradminpage(Model model){
        User user = new User();
        
        model.addAttribute("user", user);
        
        return "admin/register";
    }
    @RequestMapping(value = "/saveadmin", method = RequestMethod.POST)
    public String Registeradmin(@ModelAttribute("user") User user){
       
        
        
        
        
        service.CreateAdmin(user);
        
        
        
        return "redirect:/";
    }
}

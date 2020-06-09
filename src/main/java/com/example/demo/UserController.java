/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bibash
 */

@Controller
public class UserController {
    
   
    
    
    @Autowired
    private UserService service;
    

    
    // starting page
    @RequestMapping("/")
    public String home(){
        
        return "index";
    
    }
    
    
    //login form page
    @RequestMapping("/login")
    public String login(){
        
        return "login";
    
    }
    
  
    
 
    
    //regestring page for user
    @RequestMapping("/register")
    public String Registerpage(Model model){
        User user = new User();
        
        model.addAttribute("user", user);
        
        return "register";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String Register(@ModelAttribute("user") User user, HttpServletRequest request){
       
        
        if(service.findByusername(user.getUsername()) !=null){
            
            request.setAttribute("error", "Already register");
        return "redirect:/register";
        
        }
        service.CreateUser(user);
            return "login";
        
        
        
    }
    
    
    @RequestMapping("/changePassword/{id}")
    public ModelAndView passwordChange(@PathVariable(name ="id")int id){
        
        ModelAndView  mv = new ModelAndView("changepwd");
        
        User user = service.get(id);
        mv.addObject("user", user);
        
        return mv;
        
        
    }
    //password changing
    @RequestMapping(value = "/change", method = RequestMethod.POST)
    public String Change(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response){
        
       String pass1 = request.getParameter("password1");
       
        System.out.println(pass1);
       
       if(user.getPassword().equals(pass1)){
           
           service.changepassword(user);
           
           return "index";
       }
        
        
        return "redirect:/listuser";
    }
    
    //forget password cntroller
     @RequestMapping("/forget")
    public String ForgetPassword(){
        
        
         System.out.println("");
        
        return "forgetpassword";
    }
    @RequestMapping(value = "/saveme", method = RequestMethod.POST)
    public String forgetpassword(@ModelAttribute("user") User user){
       
        
        
       
        
        return "login";
    }
    
    
   
    
    
    
    
    
    
    
    
    @RequestMapping("/edituser/{id}")
    public ModelAndView Editpage(@PathVariable(name ="id")int id){
        
        ModelAndView  mv = new ModelAndView("edit");
        
        User user = service.get(id);
        mv.addObject("user", user);
        
        return mv;
        
        
    }
    
    @RequestMapping("/deleteuser/{id}")
    public String DeleteUser(@PathVariable(name = "id")int id){
        service.Delete(id);
        
        return "redirect:/listUser";
    }
    
//    @RequestMapping("/active/{id}")
//    public String Userblock(@PathVariable(name = "id")boolean active){
//        service.Active(active);
//        
//        return "redirect:/listUser";
//    }
    
}

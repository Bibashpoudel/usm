/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.security.Principal;
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
import org.springframework.validation.BindingResult;
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
        
        return "home";
    
    }
    
    
    //login form page
    @RequestMapping("/login")
    public String login(){
        
        
        
        
        return "login";
    
    }
    //login form page
    @RequestMapping("/home")
    public String index(Principal principal, Model model ){
        
        String username= principal.getName();
        
        model.addAttribute("username", username);
        
        return "user/index";
    
    }
    
  
    
 
    
    //regestring page for user
    @RequestMapping("/register")
    public String Registerpage(Model model){
        User user = new User();
        
        model.addAttribute("user", user );
        
        return "register";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String Register(@ModelAttribute("user") User user, Model model){
       
        
        if(service.findByusername(user.getUsername()) !=null){
            
            model.addAttribute("error", "Already register");
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
//    @RequestMapping(value = "/change", method = RequestMethod.POST)
//    public String Change(@ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response){
//        
//       String pass1 = request.getParameter("password1");
//       
//        System.out.println(pass1);
//       
//       if(user.getPassword().equals(pass1)){
//           
//           service.changepassword(user);
//           
//           return "index";
//       }
//        
//        
//        return "redirect:/listuser";
//    }
    
    //forget password controller
     @RequestMapping("/forget")
    public String ForgetPassword(){
        
        
         System.out.println("");
        
        return "forgetpassword";
    }
    @RequestMapping(value = "/changepassword", method = RequestMethod.POST)
    public String forgetpassword(@ModelAttribute("user") User user, Model model){
       
        
         if(service.findByusername(user.getUsername()) == null){
            
            model.addAttribute("notmatch", "user name not match");
        
            return "forgetpassword";
        
        }
         else
             service.forgetPassword(user);
             
        
        return "login";
    }
    
    
    @RequestMapping("/profile")
    public String UserProfile(Model model , Principal principal) throws Exception{
       
        String username = principal.getName();
        User users = service.findByusername(username);
        
        model.addAttribute("users", users);
        
        return "profile";
    }
    
    //for admin
    @RequestMapping("/registeradmin")
    public String Registeradminpage(Model model){
        User user = new User();
        
        model.addAttribute("user", user);
        
        return "admin/register";
    }
    
    
    @RequestMapping(value = "/saveadmin", method = RequestMethod.POST)
    public String Registeradmin(@ModelAttribute("user") User user){
       
        
        
        
        
        service.CreateAdmin(user);
        
        
        
        return "redirect:/ ";
    }
   
}

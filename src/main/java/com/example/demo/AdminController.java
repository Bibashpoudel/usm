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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author bibash
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
    
    
    @Autowired    
    private UserService service;
    
    
    @Autowired
    private HistoryService htrservice;
    
    
    @GetMapping("/")
    public String home(){
       
        
        
        return "admin/dashboard";
    
    
    }
    
    
    
    //for admin registration
    
    
    @RequestMapping("/registeradmin")
    public String Registeradminpage(Model model){
        User user = new User();
        
        model.addAttribute("user", user);
        
        return "admin/register";
    }
    
    
    
    //create new auser
    @RequestMapping(value = "/saveadmin", method = RequestMethod.POST)
    public String Registeradmin(@ModelAttribute("user") User user, Model model){
       
        if(service.findByusername(user.getUsername()) !=null){
            
            model.addAttribute("error", "Already register");
        
            return "register";
        
        }else
            
         service.CreateAdmin(user);
            
        return "redirect:/admin";
    
        
    }
    
    
    
    @GetMapping("/listUser")
    public String UserList(Model model, HttpServletRequest request, String username){
        
       
        List<User> listUser = service.ListUser();
         
        model.addAttribute ("listUser", listUser);
        
        model.addAttribute("counts", service.countuser());
  
        return "admin/userlist";
    
    }
    
//    //list history
//     @GetMapping("/listhistory")
//    public String Userhistory(Model model, HttpServletRequest request, String username){
//        
//       
//         List<History> listhistory = htrservice.listhistory();
//         
//        model.addAttribute ("listhistory ", listhistory );
//  
//        return "admin/history";
//    
//    }
    
    //registration for admin
    
    
    //edit user
    @RequestMapping("/edituser/{id}")
    public ModelAndView Editpage(@PathVariable(name ="id")int id){
        
        ModelAndView  mv = new ModelAndView("admin/edit");
        
        User user = service.get(id);
        mv.addObject("user", user);
        
        return mv;
        
        
    }
    //deleting user
    @RequestMapping("/deleteuser/{id}")
    public String DeleteUser(@PathVariable(name = "id")int id){
        service.Delete(id);
        
        return "redirect:/admin/listUser";
    }
    
    
    ///updating user
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String Update(@ModelAttribute("user") User user, HttpServletRequest request, BindingResult result){
       
        
        if(service.findByusername(user.getUsername()) !=null){
            
            request.setAttribute("error", "Already register");
        return "redirect:/register";
        
        }
        service.updateUser(user);
            
        return "redirect:/admin/listUser";
        
        
        
    }
}

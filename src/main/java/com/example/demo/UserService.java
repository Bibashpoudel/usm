/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author bibash
 */
@Service

public class UserService implements UserDetailsService {
    
    
    @Autowired
    private UserRepository repo;
    
    
    
    public UserService(UserRepository repo){
        this.repo=repo;
    }
    
    
    public List<User> ListUser(){
        
        
        
        return repo.findAll();
    }
    
    
    public void CreateUser(User user ){
        
        
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        user.setActive(true);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        String d = dateFormat.format(date);
        
        
        History h1 = new History();
        
        h1.setCreated_date(d);
        h1.setDescs("Registration date");

        
        
        user.setHistory(Arrays.asList(h1));
        
        
        repo.save(user);
        
        
    }
    
    public void CreateAdmin(User user ){
        
        
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        
        
//        
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRoles("ROLE_ADMIN");
        user.setActive(false);
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date date = new Date();

        String d = dateFormat.format(date);
        
        
        History h1 = new History();
        
        h1.setCreated_date(d);
        h1.setDescs("Registration date");

        
        
        user.setHistory(Arrays.asList(h1));
        
        
        repo.save(user);
        
        
    }
    
    public void changepassword(User user){
        
        
        
        repo.save(user);
        
    }
    
    
    
    
    public User get(int id){
        return repo.findById(id).get();
    }
    
    public void Delete(int id){
        
        repo.deleteById(id);
        
    }
    
   public User findByusername(String username){
       return repo.findByUsername(username);
   }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        
        
        MyUserDetail userdetail = new MyUserDetail (user);
        
        return userdetail;
        
        
        
//        user.orElseThrow(() -> new UsernameNotFoundException("not found" + username));
//            
//        return user.map(MyUserDetail::new).get();
    
    }

    
    
    
    
    
    
}

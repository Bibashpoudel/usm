/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author bibash
 */

public interface UserRepository extends JpaRepository<User, Integer>{
    
    /**
     *
     * @param username
     * @return
     */
   
    
    public User findByUsername(String username);

//    public User findOne(String username);
    
    /**
     *
     * @return
     */
    @Override
    public long count();
    
    
   
    
    
    
}

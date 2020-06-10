/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 *
 * @author bibash
 */
@Service
public class HistoryService {
    
    @Autowired
     private HistoryRepository repo;       
    
//    public List<History> findUserhis(User user) {
//    
//        return  repo.findByUserid(user);
//    }
    
    public List<History> getAllHistory(int id){
    
//        return repo.findAll();

        
        
        
        return repo.findByuserid(id);
                
    }

    
    
}

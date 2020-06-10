/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author bibash
 */
public interface HistoryRepository extends JpaRepository<History, Integer>  {
    
    
    public List<History> findByuserid(int id);
    
    
//    @Query("SELECT History.created_date,History.desc  FROM history \n" +
//"   INNER JOIN User ON (User.id = History.user_id)")
//    List<History> findByuserid(int id);
//    

    
    
}

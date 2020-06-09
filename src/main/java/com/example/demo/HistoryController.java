/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author bibash
 */
@Controller
public class HistoryController {
    
    
    
    @Autowired
    private HistoryService hs;
    
    @RequestMapping("admin/history/{id}")
    public List<History> getHistory(@PathVariable int id, Model model){
        
        List<History> history = new ArrayList<>();
        
        history = hs.getAllHistory(id);
        
        model.addAttribute("history",history);
        
        return hs.getAllHistory(id);
    
    }
    
}

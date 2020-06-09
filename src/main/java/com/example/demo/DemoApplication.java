package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

     @Autowired
     private UserRepository repo;
        
    @Override
    public void run(String... args) throws Exception {
        
        User user = new User();
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setId(1);
        user.setUsername("admin");
        user.setPassword(encoder.encode("admin123"));
        user.setRoles("ROLE_ADMIN");
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
    
    

}

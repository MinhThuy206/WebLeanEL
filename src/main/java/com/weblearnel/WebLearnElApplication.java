package com.weblearnel;

import com.weblearnel.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebLearnElApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebLearnElApplication.class, args);
    }

    @Bean(name = "USER_BEAN")
    public User setUser(){
        User u = new User();
        u.setUsername("admin");
        u.setPassword("020602");
        return u;
    }

}

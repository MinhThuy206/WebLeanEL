package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String ShowsignIn() {
        System.out.println("login");
        return "authentication/sign-in";
    }

    @GetMapping("/index")
    public String index() {
        System.out.println("index");
        return "index";
    }

    @PostMapping("/checklogin")
    public ResponseEntity<String> checkLogin(@RequestBody User user){
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            User userCheck = userService.getUser(username);
            if(userCheck.getPassword().equals(password)){
                System.out.println("login thanh cong");
                return ResponseEntity.ok("redirect:/index");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting the form: " + "Sai mật khẩu");
        } catch (Exception e) {
            System.out.println("login that bai");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error submitting the form: " + e.getMessage());
        } 
        
        
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}

package com.weblearnel.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.weblearnel.model.User;
import com.weblearnel.service.UserService;

@Controller
@RequestMapping
public class SignInController {
    @Autowired
    private UserService userService;

    @GetMapping("/user/login")
    public String ShowsignIn() {
<<<<<<< Updated upstream
        return "login";
    }

    @PostMapping("/checklogin")
    public String checkLogin(@RequestParam("username") String username, @RequestParam("password") String password){
        User user = userService.getUser(username);
        if(user.getPassword().equals(password)){
            System.out.println("login thanh cong");
            return "index";
=======
        return "authentication/sign-in";
    }

    @GetMapping("/login-admin")
    public String AdminsignIn() {
        return "admin/sign-in";
    }

    @GetMapping("/index/{user_id}")
    public String index(@PathVariable("user_id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        System.out.println("index");
        return "index";
    }

    @PostMapping("/checklogin")
    public ResponseEntity<String> checkLogin(@RequestBody User user, Model model) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            User userCheck = userService.getUser(username);
            if (userCheck.getPassword().equals(password) && userCheck.getEnabled() == true) {

                System.out.println("login thanh cong");
                if(userCheck.getRole() != null && userCheck.getRole() == 1) {
                    return ResponseEntity.ok("redirect:" + "/admin/users/list" );
                }
                model.addAttribute("user", userCheck);
                // String redirectUrl = "/index/" + user.getId();
                return ResponseEntity.ok("redirect:" + "/index/" + userCheck.getId());
            } else if(userCheck.getEnabled() != true) {
                System.out.println("login failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check your mail to activate your account");
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Check username and password again");
        } catch (Exception e) {
            System.out.println("login failed ");
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Login failed");
>>>>>>> Stashed changes
        }
        System.out.println("login that bai");
        return "login";
    }

    @GetMapping("/logout")
    public String logout(){
        return "login";
    }

}

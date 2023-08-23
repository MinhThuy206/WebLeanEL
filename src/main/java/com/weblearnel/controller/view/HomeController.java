package com.weblearnel.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping
public class HomeController {
    @GetMapping("/Word-Learning")
    public String showHomePage(){
        return "landing";
    }

}

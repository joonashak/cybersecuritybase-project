package sec.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginForm() {
        return "login";
    }
    
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam String username, 
            @RequestParam String password) {
        
        if (username.equals("admin") && password.equals("admin")) {
            return "redirect:/admin";
        } else {
            return "redirect:/login";
        }
    }
}

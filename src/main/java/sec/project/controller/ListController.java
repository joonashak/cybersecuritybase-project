package sec.project.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;

@Controller
public class ListController {
    
    private final SignupRepository signupRepository = new SignupRepository();
    
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        
        List<Signup> signups = signupRepository.findAll();
        model.addAttribute("signups", signups);
        
        model.addAttribute("admin", false);
        
        return "redirect:/form";
    }
    
    @RequestMapping(value = "view", method = RequestMethod.POST)
    public String filteredList(@RequestParam String name, Model model) {
        
        List<Signup> signups = signupRepository.findByName(name);
        model.addAttribute("signups", signups);
        
        model.addAttribute("admin", false);
        
        return "list";
    }
}

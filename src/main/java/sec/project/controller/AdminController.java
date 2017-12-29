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
public class AdminController {
    
    private final SignupRepository signupRepository = new SignupRepository();
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listAll(Model model) {
        
        List<Signup> signups = signupRepository.findAll();
        model.addAttribute("signups", signups);
        
        model.addAttribute("admin", true);
        
        return "list";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.POST)
    public String filterList(@RequestParam String filter, Model model) {
        
        List<Signup> signups = signupRepository.findByName(filter);
        model.addAttribute("signups", signups);
        
        model.addAttribute("admin", true);
        
        return "list";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam String name, Model model) {
        
        signupRepository.delete(name);
        
        List<Signup> signups = signupRepository.findAll();
        model.addAttribute("signups", signups);
        
        model.addAttribute("admin", true);
        
        return "list";
    }
}

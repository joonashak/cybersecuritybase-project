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
public class SignupController {

    private final SignupRepository signupRepository = new SignupRepository();

    @RequestMapping("*")
    public String defaultMapping() {
        return "redirect:/form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public String loadForm(@RequestParam(required = false) String admin, 
            Model model) {
        
        List<Signup> signups = signupRepository.findAll();
        model.addAttribute("signups", signups);
        return "form";
    }

    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public String submitForm(@RequestParam String name, 
            @RequestParam String address) {
        
        signupRepository.save(new Signup(name, address));
        return "done";
    }

}

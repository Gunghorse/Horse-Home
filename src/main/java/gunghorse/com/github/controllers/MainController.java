package gunghorse.com.github.controllers;

import gunghorse.com.github.auth.HorseHomeUserDetails;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path="/demo")
public class MainController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping(path="/add")
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        User n = new User();
        n.setFirstName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    @RequestMapping(value="/addJohn")
    public @ResponseBody String addNewUser () {
        User n = new User();
        n.setFirstName("John");
        n.setEmail("john@gmail.com");
        userRepository.save(n);
        return "Saved";
    }

    @RequestMapping(value="/removeJohn")
    public @ResponseBody String removeUser () {
        List<User> johns = userRepository.findAllByFirstName("John");
        for (User john : johns){
            userRepository.delete(john);
        }
        return "Removed";
    }

    @GetMapping(path="/allTrainer")
    public @ResponseBody Iterable<User> getAllUsersT() {
        return userRepository.findAll();
    }

    @GetMapping(path="/allAdmin")
    public @ResponseBody Iterable<User> getAllUsersA() {
        return userRepository.findAll();
    }

    @GetMapping(path="/allCustomer")
    public @ResponseBody Iterable<User> getAllUsersC() {
        return userRepository.findAll();
    }

    @RequestMapping(value = "/email", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserName(Authentication authentication) {
         HorseHomeUserDetails current = (HorseHomeUserDetails) authentication.getPrincipal();
         String pass = current.getPassword();
         String email = authentication.getName();
         return email + " " + pass;
    }
}
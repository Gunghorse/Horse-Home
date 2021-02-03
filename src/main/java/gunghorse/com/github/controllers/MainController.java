package gunghorse.com.github.controllers;

import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
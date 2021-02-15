package gunghorse.com.github.controllers;

import gunghorse.com.github.auth.HorseHomeUserDetails;
import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.services.TrainingService;
import gunghorse.com.github.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping(path="/user")
public class UserController {

    private final UserService userService;
    private final TrainingService trainingService;

    @Autowired
    public UserController(UserService userService, TrainingService trainingService) {
        this.userService = userService;
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers(){
        return userService.findAll();
    }

    @RequestMapping(value="/{username}", method = RequestMethod.GET)
    public @ResponseBody User getByUsername(@PathVariable("username") String username,
                                            Authentication authentication){
        String currentUserEmail = ((HorseHomeUserDetails) authentication.getPrincipal()).getUsername();
        User currentUser = userService.findByEmail(currentUserEmail);
        if (currentUser.getUsername().equals(username) || userService.isAdmin(currentUser)) {
            User queryUser = userService.findByUsername(username);
            if (queryUser != null)
                return queryUser;
            else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        else
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access is forbidden");
    }

    @RequestMapping(value = "/{username}/trainings", method = RequestMethod.GET)
    public @ResponseBody List<Training> getCurrentUsersAllTrainings(@PathVariable("username") String username,
                                                                    Authentication authentication){
        String currentUserEmail = ((HorseHomeUserDetails) authentication.getPrincipal()).getUsername();
        User currentUser = userService.findByEmail(currentUserEmail);
        if (!currentUser.getUsername().equals(username) && !userService.isAdmin(currentUser)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access is forbidden");
        }
        if (!userService.isCustomer(currentUser)){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "User is not a customer");
        }
        return trainingService.findAllByCustomer(currentUser);
    }

    /*
    TODO:
     - trainings filtering by time
     */

}

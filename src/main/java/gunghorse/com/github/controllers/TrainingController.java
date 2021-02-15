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

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path="/training")
public class TrainingController {

    private final TrainingService trainingService;

    private final UserService userService;

    @Autowired
    public TrainingController(TrainingService trainingService, UserService userService) {
        this.trainingService = trainingService;
        this.userService = userService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Training> getAllTrainings(){
        return trainingService.findAll();
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Training getById(@PathVariable("id") int id){
        Training training = trainingService.findById(id);
        if (training == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found");
        }
        return training;
    }

    @RequestMapping(value="/{id}/join", method = RequestMethod.GET)
    public @ResponseBody boolean joinTraining(@PathVariable("id") int id, Authentication authentication){
        Training training = trainingService.findById(id);
        if (training == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found");
        }
        if (training.getCapacity() == training.getCustomers().size()){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "No free places");
        }

        if (training.getStartTime().before(new Date())){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Training already took place");
        }
        String currentUserEmail = ((HorseHomeUserDetails) authentication.getPrincipal()).getUsername();
        User currentUser = userService.findByEmail(currentUserEmail);
        if (training.getCustomers().contains(currentUser)){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "User already joined this training");
        }
        training.getCustomers().add(currentUser);
        trainingService.save(training);
        return true;
    }

    @RequestMapping(value="/{id}/resign", method = RequestMethod.GET)
    public @ResponseBody boolean resignFromTraining(@PathVariable("id") int id, Authentication authentication){
        Training training = trainingService.findById(id);
        if (training == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Training not found");
        }
        if (training.getStartTime().before(new Date())){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Training already took place");
        }
        String currentUserEmail = ((HorseHomeUserDetails) authentication.getPrincipal()).getUsername();
        User currentUser = userService.findByEmail(currentUserEmail);
        if (!training.getCustomers().contains(currentUser)){
            throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "User did not join this training");
        }
        training.getCustomers().remove(currentUser);
        trainingService.save(training);
        return true;
    }

    @RequestMapping(value = "/upcoming", method = RequestMethod.GET)
    public @ResponseBody List<Training> getUpcomingTrainings(){
        return trainingService.upcomingTrainings();
    }

    @RequestMapping(value = "/upcomingFree", method = RequestMethod.GET)
    public @ResponseBody List<Training> getUpcomingFreeTrainings(){
        return trainingService.upcomingTrainingsWithFreePlaces();
    }
}

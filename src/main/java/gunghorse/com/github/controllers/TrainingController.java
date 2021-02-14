package gunghorse.com.github.controllers;

import gunghorse.com.github.model.Training;
import gunghorse.com.github.services.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/training")
public class TrainingController {

    private final TrainingService trainingService;

    @Autowired
    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public @ResponseBody List<Training> getAllTrainings(){
        return trainingService.findAll();
    }

    /*
    TODO:
     - trainings filtering by time and free places
     - training subscribing by the current user
     */
}

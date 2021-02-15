package gunghorse.com.github.services;

import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TrainingService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Training> findAll(){
        return StreamSupport.stream(trainingRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public List<Training> findAllByCustomer(User customer){
        return trainingRepository.findAllByCustomersIsContaining(customer);
    }

    public Training findById(int id){
        return trainingRepository.findById(id);
    }

    public Training save(Training training){
        return trainingRepository.save(training);
    }

    public List<Training> upcomingTrainings(){
        Date now = new Date();
        now.setYear(2021);      // :(
        return trainingRepository.findAllByStartTimeAfter(now);
    }

    public List<Training> upcomingTrainingsWithFreePlaces(){
        return upcomingTrainings().stream()
                .filter(training -> training.getCapacity() > training.getCustomers().size())
                .collect(Collectors.toList());
    }
}

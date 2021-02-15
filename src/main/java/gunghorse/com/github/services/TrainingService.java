package gunghorse.com.github.services;

import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

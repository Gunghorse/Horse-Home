package gunghorse.com.github.repositories;

import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface TrainingRepository extends CrudRepository<Training, Integer> {

    List<Training> findAllByCustomersIsContaining(User user);

    Training findById(int id);

    List<Training> findAllByStartTimeAfter(Date date);
}

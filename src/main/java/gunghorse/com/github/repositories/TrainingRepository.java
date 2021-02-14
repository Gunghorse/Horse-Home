package gunghorse.com.github.repositories;

import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TrainingRepository extends CrudRepository<Training, Integer> {

    List<Training> findAllByCustomer(Customer customer);
}

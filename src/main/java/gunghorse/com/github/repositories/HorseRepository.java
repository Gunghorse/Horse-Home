package gunghorse.com.github.repositories;

import gunghorse.com.github.model.Horse;
import org.springframework.data.repository.CrudRepository;

public interface HorseRepository extends CrudRepository<Horse, Integer> {
}

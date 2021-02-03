package gunghorse.com.github.repositories;

import gunghorse.com.github.model.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findAllByFirstName(String firstName);
}

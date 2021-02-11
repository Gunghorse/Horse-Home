package gunghorse.com.github.repositories;

import gunghorse.com.github.model.user.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
}

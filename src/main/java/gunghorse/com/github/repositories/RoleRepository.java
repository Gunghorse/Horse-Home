package gunghorse.com.github.repositories;

import gunghorse.com.github.model.user.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

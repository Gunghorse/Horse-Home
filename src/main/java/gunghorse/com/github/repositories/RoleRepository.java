package gunghorse.com.github.repositories;

import gunghorse.com.github.model.user.role.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}

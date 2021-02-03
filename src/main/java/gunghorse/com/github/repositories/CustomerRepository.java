package gunghorse.com.github.repositories;

import gunghorse.com.github.model.user.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {
}

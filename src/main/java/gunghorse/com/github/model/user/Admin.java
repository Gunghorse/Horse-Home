package gunghorse.com.github.model.user;

import gunghorse.com.github.model.user.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Admin extends User{
    public Admin(String firstName, String lastName, String email, String username, String password, Date dateOfBirth,
                   String phone, boolean enabled, List<Role> roles){
        super(firstName, lastName, email, username, password, dateOfBirth, phone, enabled, roles);
    }
}

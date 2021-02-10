package gunghorse.com.github.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    public User(String firstName, String lastName, String email, String password, Date dateOfBirth, String phone, boolean enabled, List<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.enabled = enabled;
        this.roles = roles;
    }

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Date dateOfBirth;

    private String phone;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;

}
package gunghorse.com.github.model.user.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public Role(RoleEnum role, String description) {
        this.role = role;
        this.description = description;
    }

    @Column(columnDefinition = "ENUM('CUSTOMER', 'TRAINER', 'ADMIN')")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String description;
}

package gunghorse.com.github.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    private String name;

    private String description;
}

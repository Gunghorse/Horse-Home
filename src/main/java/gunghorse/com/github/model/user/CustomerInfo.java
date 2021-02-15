package gunghorse.com.github.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor
public class CustomerInfo {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private int weight;

    private int height;

    private int levelOfAdvancement;     // TODO
}

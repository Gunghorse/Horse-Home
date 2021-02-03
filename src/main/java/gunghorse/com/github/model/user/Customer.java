package gunghorse.com.github.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter @Setter @NoArgsConstructor
public class Customer extends User{

    private int weight;

    private int height;

    private int leveOfAdvancement;  // TODO

}

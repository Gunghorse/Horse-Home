package gunghorse.com.github.model;

import gunghorse.com.github.model.user.Customer;
import gunghorse.com.github.model.user.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Horse horse;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Trainer trainer;

    @ManyToOne
    private Offer offer;

    private Date trainingStartTime;

    private Date trainingEndTime;

    private boolean isPaid;
}

package gunghorse.com.github.model;

import gunghorse.com.github.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor
public class Training {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Horse horse;

    @ManyToMany
    private List<User> customers;

    @ManyToOne
    private User trainer;

    @ManyToOne
    private Offer offer;

    private Date startTime;

    private Date endTime;

    private int capacity;

    public Training(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}

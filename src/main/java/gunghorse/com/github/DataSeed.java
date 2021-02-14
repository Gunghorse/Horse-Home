package gunghorse.com.github;

import gunghorse.com.github.auth.HorseHomeUserDetailsService;
import gunghorse.com.github.model.Training;
import gunghorse.com.github.model.user.Admin;
import gunghorse.com.github.model.user.Customer;
import gunghorse.com.github.model.user.Trainer;
import gunghorse.com.github.model.user.role.Role;
import gunghorse.com.github.model.user.role.RoleEnum;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.*;
import gunghorse.com.github.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class DataSeed implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role ADMIN = new Role(RoleEnum.ADMIN, "Admin");
        Role TRAINER = new Role(RoleEnum.TRAINER, "Trainer");
        Role CUSTOMER = new Role(RoleEnum.CUSTOMER, "Customer");

        roleRepository.saveAll(Arrays.asList(ADMIN, TRAINER, CUSTOMER));

        User johnathan = new Admin("Jonathan", "Crocodile", "johny.croc@email.com",
                "johny.croc", "1234", new Date(1986, Calendar.APRIL, 2),
                "+472334124", true, List.of(ADMIN));
        User benjamin = new Trainer("Benjamin", "Wolf", "benny.w@email.com",
                "benny", "1234", new Date(1994, Calendar.MARCH, 1),
                "+472323124", true, List.of(TRAINER));
        User sisi = new Customer("Sisi", "The Horse", "sisi@email.com",
                "sisi", "1234", new Date(2002, Calendar.OCTOBER, 23),
                "+472332424", true, List.of(CUSTOMER));

        HorseHomeUserDetailsService udsi = new HorseHomeUserDetailsService(userService);
        udsi.registerNewUserAccount(johnathan);
        udsi.registerNewUserAccount(benjamin);
        udsi.registerNewUserAccount(sisi);

        Training training1 = new Training(new Date(2021, Calendar.FEBRUARY, 19, 14, 0),
                new Date(2021, Calendar.FEBRUARY, 19, 15, 0));
        Training training2 = new Training(new Date(2021, Calendar.FEBRUARY, 19, 15, 0),
                new Date(2021, Calendar.FEBRUARY, 19, 16, 0));
        trainingRepository.save(training1);
        trainingRepository.save(training2);
    }
}

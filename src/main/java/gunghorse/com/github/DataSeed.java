package gunghorse.com.github;


import gunghorse.com.github.auth.UserDetailsServiceImpl;
import gunghorse.com.github.model.user.Role;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

@Component
public class DataSeed implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        roleRepository.deleteAll();

        Role ADMIN = new Role("ADMIN", "Admin");
        Role TRAINER = new Role("TRAINER", "Trainer");
        Role CUSTOMER = new Role("CUSTOMER", "Customer");

        roleRepository.saveAll(Arrays.asList(ADMIN, TRAINER, CUSTOMER));

        User johnathan = new User("Johnathan", "Crocodile", "johny.croc@email.com", "1234",
                new Date(1986, 3, 2), "+472334124", true, Arrays.asList(ADMIN));

        User benjamin = new User("Benjamin", "Wolf", "benny.w@email.com", "1234",
                new Date(1994, 2, 1), "+472323124", true, Arrays.asList(TRAINER));
        User sisi = new User("Sisi", "TheHorse", "sisi@email.com", "1234",
                new Date(2002, 9, 23), "+472332424", true, Arrays.asList(CUSTOMER));

        UserDetailsServiceImpl udsi = new UserDetailsServiceImpl(userRepository);
        udsi.registerNewUserAccount(johnathan);
        udsi.registerNewUserAccount(benjamin);
        udsi.registerNewUserAccount(sisi);
    }
}

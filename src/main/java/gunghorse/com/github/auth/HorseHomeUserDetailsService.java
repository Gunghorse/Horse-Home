package gunghorse.com.github.auth;

import gunghorse.com.github.auth.exceptions.EmailOrUsernameAlreadyExistsException;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.services.UserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
public class HorseHomeUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    public HorseHomeUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        return new HorseHomeUserDetails(user);
    }

    public User registerNewUserAccount(User userDto) {
        if (emailExist(userDto.getEmail())) {
            throw new EmailOrUsernameAlreadyExistsException(
                    "There already exists an account with that email address: " + userDto.getEmail());
        }

        String pass = encoder().encode(userDto.getPassword());
        userDto.setPassword(pass);

        return userService.save(userDto);
    }

    private boolean emailExist(String email) {
        return userService.findByUsername(email) != null;
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
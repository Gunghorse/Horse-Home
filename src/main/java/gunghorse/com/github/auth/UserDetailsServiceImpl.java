package gunghorse.com.github.auth;

import gunghorse.com.github.auth.exceptions.EmailOrUsernameAlreadyExistsException;
import gunghorse.com.github.model.user.User;
import gunghorse.com.github.repositories.UserRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@NoArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);

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

        return userRepository.save(userDto);
    }

    private boolean emailExist(String email) {
        return userRepository.getUserByEmail(email) != null;
    }

    private PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
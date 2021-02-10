package gunghorse.com.github.services;

import gunghorse.com.github.model.user.User;
import gunghorse.com.github.model.user.role.RoleEnum;
import gunghorse.com.github.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll(){
        return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public User findByUsername(String username){
        return userRepository.findUserByUsername(username);
    }

    public User findByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User save(User userDTO){
        return userRepository.save(userDTO);
    }

    public boolean isAdmin(User user){
        return user.getRoles().stream().anyMatch(role -> role.getRole().equals(RoleEnum.ADMIN));
    }
}

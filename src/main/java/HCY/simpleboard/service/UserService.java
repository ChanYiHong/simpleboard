package HCY.simpleboard.service;

import HCY.simpleboard.domain.user.User;
import HCY.simpleboard.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findUserByName(String name){
        return userRepository.findByName(name);
    }

}

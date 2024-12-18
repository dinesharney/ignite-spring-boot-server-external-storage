package ignitecachestore.service;

import ignitecachestore.entity.User;
import ignitecachestore.repository.UserRepository;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service to handle business logic for User entities.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    Ignite ignite;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(Long.valueOf(id)).orElse(null);
    }

    public User saveUser(User user) {
        IgniteCache<Long, User> cache = ignite.getOrCreateCache("UserCache");
        cache.put(user.getId(),user);
        return user;
    }

    public User updateUser(Long id, User updatedUser) {
        if (userRepository.existsById(Long.valueOf(id))) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser);
        }
        return null;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(Long.valueOf(id));
    }
}
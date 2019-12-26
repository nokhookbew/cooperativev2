package bewvy.se.cooperativev2.services;

import bewvy.se.cooperativev2.models.User;
import bewvy.se.cooperativev2.repositories.UsersRepository;
import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    public List<User> getAllUser() {
        return usersRepository.findAll();
    }

    public Optional<User> getUserById(int id) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(usersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on id: " + id)));
        return user;
    }

    public void addUser(User user) {
        usersRepository.save(user);
    }

    public void deleteUser(Integer id) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(usersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on id: " + id)));
        usersRepository.deleteById(id);
    }

    public Optional<User> updateUser(int id, User userDetails) throws ResourceNotFoundException {
        Optional<User> user = Optional.ofNullable(usersRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found on id: " + id)));
        if (!user.isPresent()) {
            return user;
        }
        userDetails.setId(id);
        return Optional.of(usersRepository.save(userDetails));
    }

    public Optional<User> getUserByEmail(String email) {
        return usersRepository.findByEmailAddress(email);
    }

    public Optional<User> getUserByToken(String token) {
        return usersRepository.findUserByToken(token);
    }

}

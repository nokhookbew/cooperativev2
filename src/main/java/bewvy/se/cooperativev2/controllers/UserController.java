package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.models.User;
import bewvy.se.cooperativev2.services.UserService;
import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> showData() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> showDataById(@PathVariable int id) throws ResourceNotFoundException {
        Optional<User> res = userService.getUserById(id);
        return ResponseEntity.ok().body(res);
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        userService.addUser(user);
        return user;
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable int id) throws ResourceNotFoundException {
        Optional<User> res = userService.getUserById(id);
        userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> editUser(@PathVariable int id, @RequestBody User userDetails) throws ResourceNotFoundException {
        final Optional<User> userUpdated = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(userUpdated);
    }
}

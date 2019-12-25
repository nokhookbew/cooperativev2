package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.models.User;
import bewvy.se.cooperativev2.services.AuthService;
import bewvy.se.cooperativev2.services.UserService;
import bewvy.se.cooperativev2.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    @Autowired
    UserService userService;

    @Autowired
    AuthService authService;

    @GetMapping
    public List<User> showData() {
        return userService.getAllUser();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> showDataById(@PathVariable int id) throws ResourceNotFoundException {
        Optional<User> res = userService.getUserById(id);
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<User>> showDataByEmail(@RequestBody User user) throws ResourceNotFoundException {
        Optional<User> res = userService.getUserByEmail(user.getEmail());
        return ResponseEntity.ok().body(res);
    }


    @PostMapping
    public User addUser(@RequestBody User user) {
        user.setPassword(hashPassword(user.getPassword()));
        userService.addUser(user);
        System.out.println(user.getPassword());
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

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@Valid @RequestBody User user) {

        Optional<User> dbUser = userService.getUserByEmail(user.getEmail());

        if (!dbUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        String hash = dbUser.get().getPassword();

        if (!BCrypt.checkpw(user.getPassword(), hash)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong Password!");
        }

        String token = authService.generateToken(user);

        Optional<User> optionalUser = userService.getUserByEmail(user.getEmail());
        optionalUser.get().setTokens(token);

        userService.updateUser(optionalUser.get().getId(), optionalUser.get());

        return ResponseEntity.ok("Welcome " + dbUser.get().getEmail() + "token :" + token);
    }
}

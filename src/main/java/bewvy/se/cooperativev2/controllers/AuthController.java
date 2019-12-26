package bewvy.se.cooperativev2.controllers;

import bewvy.se.cooperativev2.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping
    public ResponseEntity auth(@RequestHeader("X-IdToken") String idToken) {
        if (idToken == null || idToken.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        String token = idToken;

        if (token == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        HashMap<String, String> tokenJson = new HashMap<>();
        tokenJson.put("token", token);

        return ResponseEntity.ok(tokenJson);
    }
}

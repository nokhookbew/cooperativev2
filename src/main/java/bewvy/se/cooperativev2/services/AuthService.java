package bewvy.se.cooperativev2.services;

import bewvy.se.cooperativev2.models.Token;
import bewvy.se.cooperativev2.models.User;
import bewvy.se.cooperativev2.repositories.TokenRepository;
import bewvy.se.cooperativev2.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthService {
    @Autowired
    TokenRepository tokenRepository;

    public String generateToken(User user) {
        String token = TokenUtil.getInstance().getRandomToken() + user.getEmail();
        Token t = new Token();
        t.setToken(token);
        t.setUsers(Collections.singleton(user));
        tokenRepository.save(t);
        return token;
    }

}

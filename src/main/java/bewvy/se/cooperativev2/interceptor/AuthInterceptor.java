package bewvy.se.cooperativev2.interceptor;

import bewvy.se.cooperativev2.models.User;
import bewvy.se.cooperativev2.services.UserService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("X-Token");
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return false;
        }

        Optional<User> user = userService.getUserByToken(token);
        if (!user.isPresent()) {
            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            return false;
        }

        request.setAttribute("user", user);
        return true;
    }
}

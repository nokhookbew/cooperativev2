package bewvy.se.cooperativev2.utils;

import java.security.SecureRandom;
import java.util.Random;

public class TokenUtil {
    private static TokenUtil instance = new TokenUtil();
    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private final char[] symbols = characters.toCharArray();
    private final char[] buffer = new char[21];
    private final Random random = new SecureRandom();

    public static TokenUtil getInstance() {
        return instance;
    }

    public String getRandomToken() {
        for (int idx = 0; idx < buffer.length; ++idx)
            buffer[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buffer);
    }
}

package auth.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public boolean login(String username, String password) {
        logger.info(username + password);
        return username.equals("user") && password.equals("654321");
    }
}

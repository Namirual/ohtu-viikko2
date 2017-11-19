package ohtu.services;

import ohtu.domain.User;
import java.util.ArrayList;
import java.util.List;
import ohtu.data_access.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationService {

    @Autowired
    private UserDao userDao;

    public AuthenticationService() {
    }
    
    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public boolean logIn(String username, String password) {
        for (User user : userDao.listAll()) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)) {
                return true;
            }
        }

        return false;
    }

    public boolean createUser(String username, String password) {
        if (userDao.findByName(username) != null) {
            return false;
        }

        if (invalid(username, password)) {
            return false;
        }

        userDao.add(new User(username, password));

        return true;
    }

    private boolean invalid(String username, String password) {
        // validity check of username and password

        if (username.toCharArray().length < 3) {
            return true;
        }

        if (password.toCharArray().length < 8) {
            return true;
        }

        boolean hasSpecial = false;

        for (int val = 0; val < password.toCharArray().length; val++) {

            if (Character.codePointAt(password, val) > 122 ||
                    Character.codePointAt(password, val) < 65) {
                hasSpecial = true;
            }
        }

        if (!hasSpecial) {
            return true;
        }
        
        return false;
    }
}

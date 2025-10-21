package com.medpred.service;
import com.medpred.dao.UserRepository;
import com.medpred.model.User;
import com.medpred.security.Passwords;

public class AuthService {
    private final UserRepository repo = new UserRepository();
    public boolean login(String username, String password) {
        User u = repo.findByUsername(username);
        return u != null && Passwords.verify(password, u.getHash());
    }
    public void ensureDefaultAdmin() {
        if (repo.findByUsername("admin") == null) {
            repo.save(new User("admin", Passwords.hash("admin123"), "ADMIN"));
        }
    }
}

package pl.rmitula.springsecurityfirstapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.rmitula.springsecurityfirstapp.model.Token;
import pl.rmitula.springsecurityfirstapp.model.User;
import pl.rmitula.springsecurityfirstapp.repository.TokenRepository;
import pl.rmitula.springsecurityfirstapp.repository.UserRepository;
import pl.rmitula.springsecurityfirstapp.utils.TokenGenerator;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    public void saveToken(String username, String generatedToken) {
        User user = userRepository.findByUsername(username);
        Token token = new Token();
        token.setToken(generatedToken);
        token.setUser(user);
        tokenRepository.save(token);
    }

    public boolean isTokenValid(String accessToken) {
        if (tokenRepository.findOneByToken(accessToken) != null) {
           return true;
        }
        return false;
    }

    public Token findByToken(String accessToken) {
        return tokenRepository.findOneByToken(accessToken);
    }
}

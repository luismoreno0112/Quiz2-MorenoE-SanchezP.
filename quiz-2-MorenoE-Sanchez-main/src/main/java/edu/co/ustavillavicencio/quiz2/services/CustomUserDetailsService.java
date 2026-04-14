package edu.co.ustavillavicencio.quiz2.services;

import edu.co.ustavillavicencio.quiz2.entities.UserApp;
import edu.co.ustavillavicencio.quiz2.exception.BusinessRuleException;
import edu.co.ustavillavicencio.quiz2.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        UserApp user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BusinessRuleException("Usuario no encontrado"));

        return new User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole()))
        );
    }
}

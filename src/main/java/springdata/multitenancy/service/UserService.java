package springdata.multitenancy.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springdata.multitenancy.entity.User;
import springdata.multitenancy.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private TenantService tenantService;

    public UserService(UserRepository userRepository, TenantService tenantService) {
        this.userRepository = userRepository;
        this.tenantService = tenantService;
        this.encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Transactional
    public User createUser(User user) {
        String encodedPassword = encoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        User saved = userRepository.save(user);
        tenantService.initDatabase(user.getUsername());
        return saved;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with the specified username is not found"));
    }
}

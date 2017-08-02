package org.kryptonmlt.securecredit.service;

import java.util.HashSet;
import java.util.Set;
import org.kryptonmlt.securecredit.model.User;
import org.kryptonmlt.securecredit.repository.RoleRepository;
import org.kryptonmlt.securecredit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.kryptonmlt.securecredit.model.Role;

/**
 *
 * @author Kurt
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = new Role();
        userRole.setName("USER");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        user.setRoles(roles);
        roleRepository.save(roles);
        userRepository.save(user);
    }

    /**
     * Create Admin user
     */
    public void createAdminUser() {
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        Role userRole = new Role();
        userRole.setName("USER");
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        roles.add(adminRole);
        admin.setRoles(roles);
        roleRepository.save(roles);
        userRepository.save(admin);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

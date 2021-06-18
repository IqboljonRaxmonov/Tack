package ai.ecma.api_service1.component;

import ai.ecma.api_service1.entity.Role;
import ai.ecma.api_service1.entity.User;
import ai.ecma.api_service1.enums.RoleName;
import ai.ecma.api_service1.repository.RoleRepository;
import ai.ecma.api_service1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements CommandLineRunner {
    final
    UserRepository userRepository;
    final
    RoleRepository roleRepository;
    final
    PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    public DataLoader(UserRepository userRepository, RoleRepository roleRepository,
                      @Lazy PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> addRoles = new ArrayList<>();
        List<Role> roles = roleRepository.findAll();
        for (RoleName roleName : RoleName.values()) {
            if (!chekRole(roles, roleName))
                addRoles.add(new Role(roleName));
        }
        roleRepository.saveAll(addRoles);

        Optional<User> optionalUser = userRepository.findByEmailOrPhoneNumber("ircompanyit@gmail.com", "ircompanyit@gmail.com");
        if (!optionalUser.isPresent()) {
            Optional<Role> role = roleRepository.findByRoleName(RoleName.ROLE_OPERATOR);
            userRepository.save(
                    new User(
                            "Iqboljon",
                            "Raxmonov",
                            "Alijon o'g'li",
                            "ircompanyit@gmail.com",
                            passwordEncoder.encode("admin"),
                            "+998903000401",
                            "AA",
                            "5555555",
                            role.get(),
                            true
                    )
            );
        }

    }

    private boolean chekRole(List<Role> roles, RoleName roleName) {
        for (Role role : roles) {
            if (role.getRoleName().equals(roleName))
                return true;
        }
        return false;
    }
}

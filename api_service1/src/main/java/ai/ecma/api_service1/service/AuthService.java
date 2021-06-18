package ai.ecma.api_service1.service;

import ai.ecma.api_service1.entity.Role;
import ai.ecma.api_service1.entity.User;
import ai.ecma.api_service1.enums.RoleName;
import ai.ecma.api_service1.payload.ApiResponse;
import ai.ecma.api_service1.payload.UserDto;
import ai.ecma.api_service1.repository.RoleRepository;
import ai.ecma.api_service1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public ApiResponse registerUser(UserDto userDto) {
        User user=makeUser(userDto);
        Role role=roleRepository.findByRoleName(RoleName.ROLE_USER).get();
        user.setRole(role);
        userRepository.save(user);

        return new ApiResponse("Successfuly registered", true);
    }

    public ApiResponse getUserInfo(String passSeriya, String passNumber) {
        Optional<User> optionalUser = userRepository.findByPassSeriyaAndPassNumber(passSeriya, passNumber);
        return optionalUser.map(user -> new ApiResponse(true,getUserDto(user))).orElseGet(() -> new ApiResponse("User not found",false,null));
    }

    public Object getUserId(String passSeriya, String passNumber) {
        Optional<User> optionalUser = userRepository.findByPassSeriyaAndPassNumber(passSeriya, passNumber);
        return optionalUser.map(user -> user.getId()).orElseGet(() -> null);
    }

    private User makeUser(UserDto userDto) {
        User user = new User();
        user.setFirthName(userDto.getFirthName());
        user.setLastName(userDto.getLastName());
        user.setPatronymic(userDto.getPatronymic());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPassSeriya(userDto.getPassSeriya());
        user.setPassNumber(userDto.getPassNumber());
        user.setEnabled(true);

        return user;
    }

    public UserDto getUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirthName(user.getFirthName());
        userDto.setLastName(user.getLastName());
        userDto.setPatronymic(user.getPatronymic());
        userDto.setEmail(user.getEmail());
        userDto.setPhoneNumber(user.getPhoneNumber());

        return userDto;
    }

    public boolean existUserNameOrPhoneNumber(String userName) {
        return userRepository.existsByEmailOrPhoneNumber(userName, userName);
    }

    public boolean existsByPassSeriyaAndPassNumber(String passSeriye, String passNumber) {
        return userRepository.existsByPassSeriyaAndPassNumber(passSeriye, passNumber);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findByEmailOrPhoneNumber(userName.toLowerCase(), userName).orElseThrow(() -> new UsernameNotFoundException("get user name"));
    }

    public User getUser(String emile) {
        return userRepository.findByEmailOrPhoneNumber(emile,emile).orElseGet(null);
    }
}

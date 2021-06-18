package ai.ecma.api_service1.repository;

import ai.ecma.api_service1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmailOrPhoneNumber(String email, String phoneNumber);

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

    boolean existsByPassSeriyaAndPassNumber(String passSeriya, String passNumber);

    Optional<User> findByPassSeriyaAndPassNumber(String passSeriya, String passNumber);
}
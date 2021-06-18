package ai.ecma.api_service2.repository;

import ai.ecma.api_service2.entity.Credit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditRepository extends JpaRepository<Credit,UUID> {
}

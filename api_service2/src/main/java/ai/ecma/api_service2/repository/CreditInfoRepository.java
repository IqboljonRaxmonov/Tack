package ai.ecma.api_service2.repository;

import ai.ecma.api_service2.entity.CreditInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditInfoRepository extends JpaRepository<CreditInfo, UUID> {
}

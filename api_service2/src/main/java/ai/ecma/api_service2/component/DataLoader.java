package ai.ecma.api_service2.component;

import ai.ecma.api_service2.entity.Credit;
import ai.ecma.api_service2.repository.CreditRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    final
    CreditRepository creditRepository;

    public DataLoader(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Credit> credits = creditRepository.findAll();
        if (credits.size()==0)
            creditRepository.save(
                    new Credit(0.05,1D,500000,10000000,true)
            );
    }
}

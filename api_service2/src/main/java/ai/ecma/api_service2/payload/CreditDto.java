package ai.ecma.api_service2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private String message;

    private Double monthlyBodyAmount;

    private Double monthlyPercentAmount;

    private Double totalAmount;

    private Double percent;
}

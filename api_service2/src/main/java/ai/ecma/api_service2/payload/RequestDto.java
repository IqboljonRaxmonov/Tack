package ai.ecma.api_service2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDto {

    private String passSeriya;

    private String  passNumber;

    private Double salary;

    private Double creditAmount;
}

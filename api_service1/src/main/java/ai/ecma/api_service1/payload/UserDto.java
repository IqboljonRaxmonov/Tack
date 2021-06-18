package ai.ecma.api_service1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    private String firthName;

    private String lastName;

    private String patronymic;

    private String email;

    private String phoneNumber;

    private String password;

    private String prePassword;

    private String passSeriya;

    private String  passNumber;
}

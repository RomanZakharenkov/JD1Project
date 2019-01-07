package by.rzmarket.dto;

import by.rzmarket.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private Role role;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}

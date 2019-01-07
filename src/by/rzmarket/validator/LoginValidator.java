package by.rzmarket.validator;

import by.rzmarket.dto.LoginUserDto;
import by.rzmarket.entity.User;

public class LoginValidator {


//    @Override
    public static boolean isValid(User user, LoginUserDto loginUserDto) {
        return loginUserDto.getPassword().equals(user.getPassword());
    }
}

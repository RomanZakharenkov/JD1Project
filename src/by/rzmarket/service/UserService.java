package by.rzmarket.service;

import by.rzmarket.dao.UserDao;
import by.rzmarket.dto.LoginUserDto;
import by.rzmarket.dto.ShortInfoUserDto;
import by.rzmarket.entity.User;
import by.rzmarket.servlet.Login;
import by.rzmarket.validator.LoginValidator;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import sun.util.resources.ga.LocaleNames_ga;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserService {

    private static final UserService INSTANCE = new UserService();

    public User login(LoginUserDto loginUserDto) {
        User result = null;
        User user = UserDao.getInstance().getUserByEmail(loginUserDto.getEmail()).get();
        if (LoginValidator.isValid(user, loginUserDto)) {
            result = user;
            result.setPassword(null);
        }
        return result;
    }

    public User getById(Integer id) {
        return UserDao.getInstance().getById(id).get();
    }

//    public User getShortInfoUserDto(Integer id) {
//        return UserDao.getInstance().getById(id)
//                .map(it -> ShortInfoUserDto.builder()
//                        .id(it.getId())
//                        .firstName(it.getFirstName())
//                        .lastName(it.getLastName())
//                        .email(it.getEmail())
//                        .number(it.getNumber())
//                        .build())
//                .orElse(null);
//    }

    public boolean update(User user) {
        return UserDao.getInstance().update(user);
    }

    public boolean isValid(LoginUserDto loginUserDto) {
        User user = User.builder()
                .email(loginUserDto.getEmail())
                .password(loginUserDto.getPassword())
                .build();
        return UserDao.getInstance().isValid(user);
    }

    public List<User> getAll() {
        return UserDao.getInstance().getAll();
    }

    public Integer add(User user) {
        return UserDao.getInstance().add(user);
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}

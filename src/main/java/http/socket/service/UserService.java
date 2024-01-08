package http.socket.service;

import http.socket.dao.UserDao;
import http.socket.dto.CreateUserDto;
import http.socket.dto.UserDto;
import http.socket.entity.User;
import http.socket.exception.ValidationException;
import http.socket.mapper.CreateUserMapper;
import http.socket.mapper.UserMapper;
import http.socket.validator.CreateUserValidator;
import http.socket.validator.ValidationResult;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.util.Optional;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final CreateUserValidator createUserValidator = CreateUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    private final CreateUserMapper createUserMapper = CreateUserMapper.getInstance();
    private final UserMapper userMapper = UserMapper.getInstance();
    private final ImageService imageService = ImageService.getInstance();
    public Optional<UserDto> login(String email, String password){
        return userDao.findByEmailAndPassword(email, password)
                .map(userMapper::mapFrom);
    }
    @SneakyThrows
    public Integer create(CreateUserDto userDto){
        ValidationResult validationResult = createUserValidator.isValid(userDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        User userEntity = createUserMapper.mapFrom(userDto);
        imageService.upload(userEntity.getImage(), userDto.getImage().getInputStream());
        userDao.save(userEntity);


        return userEntity.getId();

    }
    public static UserService getInstance(){
        return INSTANCE;
    }
}

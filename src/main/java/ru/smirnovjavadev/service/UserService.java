package ru.smirnovjavadev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.smirnovjavadev.dto.UserDto;
import ru.smirnovjavadev.entity.UserEntity;
import ru.smirnovjavadev.exception.MyException;
import ru.smirnovjavadev.mapper.UserMapper;
import ru.smirnovjavadev.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserDto> getAll() {
        return userMapper.toUserDtos(userRepository.findAll());
    }

    public void save(UserDto userDto) throws MyException {
        userRepository.save(userMapper.toUserEntity(userDto));
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateById(Long id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow();

        userEntity.setName(userDto.getName());
        userEntity.setAge(userDto.getAge());

        userRepository.save(userEntity);
    }

    public List<UserDto> findAllOrderByAge(Integer age) {
        List<UserEntity> userEntities =
                userRepository.findUserEntitiesOrderByAgeGreaterThanOrderByAge(age);
        return userMapper.toUserDtos(userEntities);
    }

}

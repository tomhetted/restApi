package ru.smirnovjavadev.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import ru.smirnovjavadev.dto.UserDto;
import ru.smirnovjavadev.entity.UserEntity;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    List<UserDto> toUserDtos(List<UserEntity> userEntity);

    UserDto map(UserEntity userEntity);

    UserEntity toUserEntity(UserDto userDto);

    //UserDto toUserDto(UserEntity user);
}

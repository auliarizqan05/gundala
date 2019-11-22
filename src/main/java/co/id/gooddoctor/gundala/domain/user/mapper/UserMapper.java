package co.id.gooddoctor.gundala.domain.user.mapper;

import co.id.gooddoctor.gundala.domain.user.dto.LoginDto;
import co.id.gooddoctor.gundala.domain.user.dto.UserDto;
import co.id.gooddoctor.gundala.domain.user.entity.User;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User loginDtoToEntity(LoginDto loginDto);

    @Mappings({
            @Mapping(source = "roles", target = "roles")
    })
    User dtoToEntity(UserDto userDto);

    @Mappings({
            @Mapping(source = "roles", target = "roles")
    })
    UserDto entityToLoginResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    User dtoToEntity(UserDto userDto, @MappingTarget User user);

}

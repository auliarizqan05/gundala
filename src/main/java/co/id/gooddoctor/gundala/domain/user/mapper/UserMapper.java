package co.id.gooddoctor.gundala.domain.user.mapper;

import co.id.gooddoctor.gundala.domain.user.dto.LoginDto;
import co.id.gooddoctor.gundala.domain.user.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User loginDtoToEntity(LoginDto loginDto);

}

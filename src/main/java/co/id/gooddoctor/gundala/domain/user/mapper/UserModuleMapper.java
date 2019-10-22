package co.id.gooddoctor.gundala.domain.user.mapper;

import co.id.gooddoctor.gundala.domain.user.dto.ModuleDto;
import co.id.gooddoctor.gundala.domain.user.dto.UserModuleDto;
import co.id.gooddoctor.gundala.domain.user.entity.Module;
import co.id.gooddoctor.gundala.domain.user.entity.UserModule;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserModuleMapper {

    UserModuleMapper INSTANCE = Mappers.getMapper(UserModuleMapper.class);

    UserModule dtoToEntity(UserModuleDto userModuleDto);
}

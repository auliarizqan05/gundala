package co.id.gooddoctor.gundala.domain.user.mapper;

import co.id.gooddoctor.gundala.domain.user.dto.ModuleDto;
import co.id.gooddoctor.gundala.domain.user.dto.RoleDto;
import co.id.gooddoctor.gundala.domain.user.entity.Module;
import co.id.gooddoctor.gundala.domain.user.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role dtoToEntity(RoleDto roleDto);
}

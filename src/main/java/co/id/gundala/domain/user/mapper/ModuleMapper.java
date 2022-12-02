package co.id.gundala.domain.user.mapper;

import co.id.gundala.domain.user.dto.ModuleDto;
import co.id.gundala.domain.user.entity.Module;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ModuleMapper {

    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    Module dtoToEntity(ModuleDto moduleDto);
}

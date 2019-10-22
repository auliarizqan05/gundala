package co.id.gooddoctor.gundala.domain.user.mapper;

import co.id.gooddoctor.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import co.id.gooddoctor.gundala.domain.user.dto.ModuleDto;
import co.id.gooddoctor.gundala.domain.user.entity.Module;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ModuleMapper {

    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    Module dtoToEntity(ModuleDto moduleDto);
}

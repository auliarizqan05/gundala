package co.id.gooddoctor.gundala.domain.settlement.mapper;

import co.id.gooddoctor.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gooddoctor.gundala.domain.settlement.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);

    Merchant dtoToEntity(MerchantDTO merchantDTO);
}

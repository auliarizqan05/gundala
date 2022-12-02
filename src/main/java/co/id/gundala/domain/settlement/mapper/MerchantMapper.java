package co.id.gundala.domain.settlement.mapper;

import co.id.gundala.domain.settlement.dto.MerchantDTO;
import co.id.gundala.domain.settlement.entity.Merchant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface MerchantMapper {

    MerchantMapper INSTANCE = Mappers.getMapper(MerchantMapper.class);

    Merchant dtoToEntity(MerchantDTO merchantDTO);
}

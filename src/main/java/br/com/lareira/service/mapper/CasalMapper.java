package br.com.lareira.service.mapper;


import br.com.lareira.domain.*;
import br.com.lareira.service.dto.CasalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Casal} and its DTO {@link CasalDTO}.
 */
@Mapper(componentModel = "spring", uses = {LareiraMapper.class})
public interface CasalMapper extends EntityMapper<CasalDTO, Casal> {

    @Mapping(source = "idLareira.id", target = "idLareiraId")
    CasalDTO toDto(Casal casal);

    @Mapping(source = "idLareiraId", target = "idLareira")
    Casal toEntity(CasalDTO casalDTO);

    default Casal fromId(Long id) {
        if (id == null) {
            return null;
        }
        Casal casal = new Casal();
        casal.setId(id);
        return casal;
    }
}

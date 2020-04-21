package br.com.lareira.service.mapper;


import br.com.lareira.domain.*;
import br.com.lareira.service.dto.LareiraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lareira} and its DTO {@link LareiraDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface LareiraMapper extends EntityMapper<LareiraDTO, Lareira> {


    @Mapping(target = "ids", ignore = true)
    @Mapping(target = "removeId", ignore = true)
    Lareira toEntity(LareiraDTO lareiraDTO);

    default Lareira fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lareira lareira = new Lareira();
        lareira.setId(id);
        return lareira;
    }
}

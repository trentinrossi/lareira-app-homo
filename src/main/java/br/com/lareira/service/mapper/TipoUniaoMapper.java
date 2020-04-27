package br.com.lareira.service.mapper;


import br.com.lareira.domain.*;
import br.com.lareira.service.dto.TipoUniaoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TipoUniao} and its DTO {@link TipoUniaoDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TipoUniaoMapper extends EntityMapper<TipoUniaoDTO, TipoUniao> {



    default TipoUniao fromId(Long id) {
        if (id == null) {
            return null;
        }
        TipoUniao tipoUniao = new TipoUniao();
        tipoUniao.setId(id);
        return tipoUniao;
    }
}

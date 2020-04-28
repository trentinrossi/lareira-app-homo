package br.com.lareira.service.mapper;


import br.com.lareira.domain.*;
import br.com.lareira.service.dto.CasalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Casal} and its DTO {@link CasalDTO}.
 */
@Mapper(componentModel = "spring", uses = {LareiraMapper.class, TipoUniaoMapper.class})
public interface CasalMapper extends EntityMapper<CasalDTO, Casal> {

    @Mapping(source = "lareira.id", target = "lareiraId")
    @Mapping(source = "tipoUniao.id", target = "tipoUniaoId")
    @Mapping(source = "casalPadrinho.id", target = "casalPadrinhoId")
    CasalDTO toDto(Casal casal);

    @Mapping(target = "filhos", ignore = true)
    @Mapping(target = "removeFilho", ignore = true)
    @Mapping(target = "apadrinhados", ignore = true)
    @Mapping(target = "removeApadrinhado", ignore = true)
    @Mapping(source = "lareiraId", target = "lareira")
    @Mapping(source = "tipoUniaoId", target = "tipoUniao")
    @Mapping(source = "casalPadrinhoId", target = "casalPadrinho")
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

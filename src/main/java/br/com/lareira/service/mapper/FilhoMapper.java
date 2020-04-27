package br.com.lareira.service.mapper;


import br.com.lareira.domain.*;
import br.com.lareira.service.dto.FilhoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Filho} and its DTO {@link FilhoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CasalMapper.class})
public interface FilhoMapper extends EntityMapper<FilhoDTO, Filho> {

    @Mapping(source = "idCasal.id", target = "idCasalId")
    FilhoDTO toDto(Filho filho);

    @Mapping(source = "idCasalId", target = "idCasal")
    Filho toEntity(FilhoDTO filhoDTO);

    default Filho fromId(Long id) {
        if (id == null) {
            return null;
        }
        Filho filho = new Filho();
        filho.setId(id);
        return filho;
    }
}

package br.com.lareira.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class TipoUniaoMapperTest {

    private TipoUniaoMapper tipoUniaoMapper;

    @BeforeEach
    public void setUp() {
        tipoUniaoMapper = new TipoUniaoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(tipoUniaoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(tipoUniaoMapper.fromId(null)).isNull();
    }
}

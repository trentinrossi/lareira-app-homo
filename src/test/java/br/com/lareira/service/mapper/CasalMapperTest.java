package br.com.lareira.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CasalMapperTest {

    private CasalMapper casalMapper;

    @BeforeEach
    public void setUp() {
        casalMapper = new CasalMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(casalMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(casalMapper.fromId(null)).isNull();
    }
}

package br.com.lareira.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class LareiraMapperTest {

    private LareiraMapper lareiraMapper;

    @BeforeEach
    public void setUp() {
        lareiraMapper = new LareiraMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(lareiraMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(lareiraMapper.fromId(null)).isNull();
    }
}

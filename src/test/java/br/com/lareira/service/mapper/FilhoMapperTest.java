package br.com.lareira.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class FilhoMapperTest {

    private FilhoMapper filhoMapper;

    @BeforeEach
    public void setUp() {
        filhoMapper = new FilhoMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(filhoMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(filhoMapper.fromId(null)).isNull();
    }
}

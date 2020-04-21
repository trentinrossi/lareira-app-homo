package br.com.lareira.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class LareiraDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(LareiraDTO.class);
        LareiraDTO lareiraDTO1 = new LareiraDTO();
        lareiraDTO1.setId(1L);
        LareiraDTO lareiraDTO2 = new LareiraDTO();
        assertThat(lareiraDTO1).isNotEqualTo(lareiraDTO2);
        lareiraDTO2.setId(lareiraDTO1.getId());
        assertThat(lareiraDTO1).isEqualTo(lareiraDTO2);
        lareiraDTO2.setId(2L);
        assertThat(lareiraDTO1).isNotEqualTo(lareiraDTO2);
        lareiraDTO1.setId(null);
        assertThat(lareiraDTO1).isNotEqualTo(lareiraDTO2);
    }
}

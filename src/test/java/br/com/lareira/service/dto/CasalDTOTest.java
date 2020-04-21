package br.com.lareira.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class CasalDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(CasalDTO.class);
        CasalDTO casalDTO1 = new CasalDTO();
        casalDTO1.setId(1L);
        CasalDTO casalDTO2 = new CasalDTO();
        assertThat(casalDTO1).isNotEqualTo(casalDTO2);
        casalDTO2.setId(casalDTO1.getId());
        assertThat(casalDTO1).isEqualTo(casalDTO2);
        casalDTO2.setId(2L);
        assertThat(casalDTO1).isNotEqualTo(casalDTO2);
        casalDTO1.setId(null);
        assertThat(casalDTO1).isNotEqualTo(casalDTO2);
    }
}

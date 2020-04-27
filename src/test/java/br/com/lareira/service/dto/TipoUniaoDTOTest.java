package br.com.lareira.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class TipoUniaoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoUniaoDTO.class);
        TipoUniaoDTO tipoUniaoDTO1 = new TipoUniaoDTO();
        tipoUniaoDTO1.setId(1L);
        TipoUniaoDTO tipoUniaoDTO2 = new TipoUniaoDTO();
        assertThat(tipoUniaoDTO1).isNotEqualTo(tipoUniaoDTO2);
        tipoUniaoDTO2.setId(tipoUniaoDTO1.getId());
        assertThat(tipoUniaoDTO1).isEqualTo(tipoUniaoDTO2);
        tipoUniaoDTO2.setId(2L);
        assertThat(tipoUniaoDTO1).isNotEqualTo(tipoUniaoDTO2);
        tipoUniaoDTO1.setId(null);
        assertThat(tipoUniaoDTO1).isNotEqualTo(tipoUniaoDTO2);
    }
}

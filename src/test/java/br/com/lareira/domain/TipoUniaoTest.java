package br.com.lareira.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class TipoUniaoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TipoUniao.class);
        TipoUniao tipoUniao1 = new TipoUniao();
        tipoUniao1.setId(1L);
        TipoUniao tipoUniao2 = new TipoUniao();
        tipoUniao2.setId(tipoUniao1.getId());
        assertThat(tipoUniao1).isEqualTo(tipoUniao2);
        tipoUniao2.setId(2L);
        assertThat(tipoUniao1).isNotEqualTo(tipoUniao2);
        tipoUniao1.setId(null);
        assertThat(tipoUniao1).isNotEqualTo(tipoUniao2);
    }
}

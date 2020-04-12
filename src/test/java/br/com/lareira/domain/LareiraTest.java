package br.com.lareira.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class LareiraTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lareira.class);
        Lareira lareira1 = new Lareira();
        lareira1.setId(1L);
        Lareira lareira2 = new Lareira();
        lareira2.setId(lareira1.getId());
        assertThat(lareira1).isEqualTo(lareira2);
        lareira2.setId(2L);
        assertThat(lareira1).isNotEqualTo(lareira2);
        lareira1.setId(null);
        assertThat(lareira1).isNotEqualTo(lareira2);
    }
}

package br.com.lareira.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class CasalTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Casal.class);
        Casal casal1 = new Casal();
        casal1.setId(1L);
        Casal casal2 = new Casal();
        casal2.setId(casal1.getId());
        assertThat(casal1).isEqualTo(casal2);
        casal2.setId(2L);
        assertThat(casal1).isNotEqualTo(casal2);
        casal1.setId(null);
        assertThat(casal1).isNotEqualTo(casal2);
    }
}

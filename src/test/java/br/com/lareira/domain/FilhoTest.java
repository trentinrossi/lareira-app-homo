package br.com.lareira.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class FilhoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Filho.class);
        Filho filho1 = new Filho();
        filho1.setId(1L);
        Filho filho2 = new Filho();
        filho2.setId(filho1.getId());
        assertThat(filho1).isEqualTo(filho2);
        filho2.setId(2L);
        assertThat(filho1).isNotEqualTo(filho2);
        filho1.setId(null);
        assertThat(filho1).isNotEqualTo(filho2);
    }
}

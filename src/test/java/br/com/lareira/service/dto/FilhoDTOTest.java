package br.com.lareira.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.lareira.web.rest.TestUtil;

public class FilhoDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(FilhoDTO.class);
        FilhoDTO filhoDTO1 = new FilhoDTO();
        filhoDTO1.setId(1L);
        FilhoDTO filhoDTO2 = new FilhoDTO();
        assertThat(filhoDTO1).isNotEqualTo(filhoDTO2);
        filhoDTO2.setId(filhoDTO1.getId());
        assertThat(filhoDTO1).isEqualTo(filhoDTO2);
        filhoDTO2.setId(2L);
        assertThat(filhoDTO1).isNotEqualTo(filhoDTO2);
        filhoDTO1.setId(null);
        assertThat(filhoDTO1).isNotEqualTo(filhoDTO2);
    }
}

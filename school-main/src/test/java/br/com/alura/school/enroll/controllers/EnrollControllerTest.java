package br.com.alura.school.enroll.controllers;

import br.com.alura.school.enroll.dto.request.NewEnrollRequest;
import br.com.alura.school.enroll.dto.response.EnrollResponse;
import br.com.alura.school.enroll.services.EnrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EnrollControllerTest {

    @MockBean
    private EnrollService enrollService;

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Test
    void should_save_new_enroll() throws Exception {
        var courseCode = "java-2";
        var newEnrollRequest = new NewEnrollRequest("bruna_delmouro");

        enrollService.saveEnroll(courseCode, newEnrollRequest);

        mockMvc.perform(post("/courses/" + courseCode + "/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(enrollService).saveEnroll(courseCode, newEnrollRequest);
    }

    @ParameterizedTest
    @CsvSource({
            "''",
            "an-username-that-is-really-really-big"
    })
    void should_throw_bad_request_exception_when_username_is_not_correct(String username) throws Exception {
        var courseCode = "java-2";
        var newEnrollRequest = new NewEnrollRequest(username);

        mockMvc.perform(post("/courses/" + courseCode + "/enroll")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonMapper.writeValueAsString(newEnrollRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_get_enrolls() throws Exception {
        var newEnrollResponse1 = new EnrollResponse("bruna@email.com", 2L);
        var newEnrollResponse2 = new EnrollResponse("alex@email.com", 3L);

        when(enrollService.getEnrolls()).thenReturn(List.of(newEnrollResponse1, newEnrollResponse2));

        mockMvc.perform(get("/courses/enroll/report")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)))
                .andExpect(jsonPath("$[0].email", is("bruna@email.com")))
                .andExpect(jsonPath("$[0].quantidade_matriculas", is(2)))
                .andExpect(jsonPath("$[1].email", is("alex@email.com")))
                .andExpect(jsonPath("$[1].quantidade_matriculas", is(3)));

        verify(enrollService).getEnrolls();
    }
}

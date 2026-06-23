package org.nico.ddd;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.nico.ddd.application.port.out.AvisRepository;
import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.avis.Message;
import org.nico.ddd.domain.entity.avis.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class DddApplicationTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @Autowired private AvisRepository avisRepository;

  @Test
  void testControllerSignalements() throws Exception {
    avisRepository.sauver(
        Avis.builder()
            .identifiant(new AvisId("id1"))
            .note(new Note(1))
            .message(new Message("Mon avis"))
            .datePublication(LocalDateTime.now())
            .build());

    Map<String, Object> body = new HashMap<>();
    body.put("identifiant", "id1");
    mockMvc
        .perform(
            post("/signalements")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(body)))
        .andDo(print())
        .andExpect(status().isCreated());
  }
}

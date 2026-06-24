package org.nico.ddd;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.nico.ddd.application.port.out.AvisRepository;
import org.nico.ddd.application.port.out.SignalementRepository;
import org.nico.ddd.domain.entity.avis.Avis;
import org.nico.ddd.domain.entity.avis.AvisId;
import org.nico.ddd.domain.entity.avis.Message;
import org.nico.ddd.domain.entity.avis.Note;
import org.nico.ddd.domain.entity.signalement.Signalement;
import org.nico.ddd.domain.entity.signalement.SignalementId;
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
  @Autowired private SignalementRepository signalementRepository;

  @Test
  void testControllerSignalements() throws Exception {
    String identifiantAvis = "id1";
    ajouterAvisAvecIdentifiant(identifiantAvis);

    mockMvc
        .perform(
            post("/signalements")
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(creerBodyPostSignalementAvec(identifiantAvis)))
        .andDo(print())
        .andExpect(status().isCreated());
  }

  @Test
  void testControllerAvis() throws Exception {
    String identifiantAvis = "id2";
    ajouterAvisAvecIdentifiant(identifiantAvis);
    ajouterSignalementPourAvis(identifiantAvis);

    mockMvc
        .perform(
            delete("/avis/" + identifiantAvis)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNoContent());
  }

  private void ajouterSignalementPourAvis(String identifiantAvis) {
    signalementRepository.sauver(
        new Signalement(new SignalementId("id-sign-1"), new AvisId(identifiantAvis)));
  }

  private void ajouterAvisAvecIdentifiant(String identifiant) {
    avisRepository.sauver(
        Avis.builder()
            .identifiant(new AvisId(identifiant))
            .note(new Note(1))
            .message(new Message("Mon avis"))
            .datePublication(LocalDateTime.now())
            .build());
  }

  private String creerBodyPostSignalementAvec(String identifiantAvis) {
    Map<String, Object> body = new HashMap<>();
    body.put("identifiant", identifiantAvis);
    return objectMapper.writeValueAsString(body);
  }
}

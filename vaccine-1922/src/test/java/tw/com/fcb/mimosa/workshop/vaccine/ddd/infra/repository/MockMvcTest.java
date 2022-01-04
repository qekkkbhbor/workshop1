package tw.com.fcb.mimosa.workshop.vaccine.ddd.infra.repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import tw.com.fcb.mimosa.test.MimosaTest;

@MimosaTest
@AutoConfigureMockMvc
class MockMvcTest {

  @Autowired
  MockMvc mvc;

  @Autowired
  ObjectMapper mapper;

  //	@MockBean
  //	FakeController mock;

  @Test
  void listAll() throws Exception {
    var body = mvc.perform(MockMvcRequestBuilders.get("/test").accept(MediaType.APPLICATION_JSON))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andReturn().getResponse().getContentAsString();

    Assertions.assertThat(body).isEqualTo("100");
  }

  @Test
  void echo() throws Exception {
    var name = new Name();
    name.setFirstName("my-name");
    mvc.perform(MockMvcRequestBuilders.post("/test/echo").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(name)))
        .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
        .andExpect(MockMvcResultMatchers.content().string("Hello" + name.getFirstName()));

  }

  @RestController
  @RequestMapping("/test")
  static class FakeController {

    @GetMapping
    int ListAll() {
      return 100;
    }

    @PostMapping("/echo")
    String echo(@RequestBody Name name) {
      return "Hello" + name.getFirstName();
    }
  }

  @Data
  static class Name {
    String firstName;
  }

}

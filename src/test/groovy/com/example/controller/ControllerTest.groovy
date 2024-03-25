package com.example.controller

import com.example.simulation.Simulate
import org.spockframework.spring.SpringBean
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(Controller.class)
class ControllerTest extends Specification {


    private MockMvc mockMvc

    @SpringBean
    private Simulate simulate = Mock()

//    @MockBean
//    private Simulate simulate;

    def "when GET is performed then the response has status 200"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(new Controller(simulate)).build()
        expect: "Status is 200"
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
    }

    def "blah blah blah"() {
        given:
        mockMvc = MockMvcBuilders.standaloneSetup(new Controller(simulate)).build()
        expect: "Status is 200"
        mockMvc.perform(post("/test")
                .param("homeTeam", "Manchester City") // Add your first parameter
                .param("awayTeam", "Tottenham Hotspur"))
                .andExpect(status().isOk())
    }
}


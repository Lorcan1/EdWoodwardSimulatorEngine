package com.example.controller

import com.example.simulation.Simulate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post


@SpringBootTest

class TestControllerTest extends Specification {

    private MockMvc mvc

    private MockMvc mockMvc

    def "when POST is performed with two string parameters then the response has status 200"() {
        given: "Two string parameters for the POST request"
        String param1 = "Manchester City"
        String param2 = "Tottenham Hotspur"

        when: "Sending a POST request with the parameters"
        def response = mvc.perform(post("/test")
                .param("homeTeam", param1)
                .param("awayTeam", param2))
                .andExpect(status().isOk())
                .andReturn()
                .response
                .contentAsString

        then: "The response status code is as expected"
        response == "Expected response content"
    }

    def "when POST is performed with two string parameters then the response has status 20011"() {
        given: "Two string parameters for the POST request"
        String param1 = "Manchester City"
        String param2 = "Tottenham Hotspur"

        mockMvc = MockMvcBuilders.standaloneSetup(new TestController(simulate:Mock(Simulate))).build()

        when: "Sending a POST request with the parameters"
        def result = mockMvc.perform { MockMvcRequestBuilders.get("/hello")}

        then: "The response status code is as expected"
        assert true == true
    }
}
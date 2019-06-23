package com.kaitait.email.controller


import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class HealthControllerSpec extends Specification {
    HealthController controller
    MockMvc mockMvc

    def healthRequestUri = '/api/health'


    void setup() {
        controller = new HealthController()
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .setControllerAdvice(new ControllerExceptionHandler())
                .build()
    }

    def "/health endpoint works as expected"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(healthRequestUri))

        then:
        MvcResult result = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
        result.getResponse().getContentAsString() == "I'm alive :)"
    }
}

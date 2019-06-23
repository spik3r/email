package com.kaitait.email.controller

import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

class ApiDocControllerSpec extends Specification {
    ApiDocController controller
    MockMvc mockMvc

    def swaggerRequestUri = '/apidoc'
    def swaggerUiRequestUri = '/apidoc/ui'


    void setup() {
        controller = new ApiDocController()
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .setControllerAdvice(new ControllerExceptionHandler())
                .build()
    }
    def "OpenApiDocumentation works as expected"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(swaggerRequestUri))

        then:
        actions.andExpect(MockMvcResultMatchers.status().isFound()).andReturn()
    }

    def "OpenApiUiDocumentation works as expected"() {
        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(swaggerUiRequestUri))

        then:
        actions.andExpect(MockMvcResultMatchers.status().isFound()).andReturn()
    }
}

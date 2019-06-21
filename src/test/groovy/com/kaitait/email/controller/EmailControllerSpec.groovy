package com.kaitait.email.controller

import com.kaitait.email.service.EmailService
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification
import spock.lang.Unroll

class EmailControllerSpec extends Specification {
    EmailController controller
    MockMvc mockMvc

    EmailService emailService
    def requestUri = '/api/'
    def mailRequestUri = '/api/mail'


    void setup() {
        emailService = Mock()
        controller = new EmailController(emailService)
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .alwaysDo(MockMvcResultHandlers.print())
                .setControllerAdvice(new ControllerExceptionHandler())
                .build()
    }

    @Unroll
    def "index method should perform as expected for 'non true' pathVariable #pathVariable"() {

        when:
        ResponseEntity response = controller.index(pathVariable)

        then:
        noExceptionThrown()
        0 * emailService._

        and:
        response.getBody() == expectedResult

        where:
        pathVariable             | expectedResult
        null                     | "Index called!"
        "abc"                    | "Index called!"
        "foo"                    | "Index called!"
        "false"                  | "Index called!"
    }

    @Unroll
    void "/api endpoint should perform as expected for 'non true' pathVariable #pathVariable"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(requestUri + pathVariable))

        then:
        noExceptionThrown()
        0 * emailService._

        and:
        MvcResult result = actions.andExpect(MockMvcResultMatchers.status().isOk()).andReturn()
        result.getResponse().getContentAsString() == expectedResult

        where:
        pathVariable             | expectedResult
        null                     | "Index called!"
        "abc"                    | "Index called!"
        "foo"                    | "Index called!"
        "false"                  | "Index called!"

    }

    def "index method should throws Runtime exception for 'true' pathVariable #pathVariable"() {

        when:
        controller.index(pathVariable)

        then:
        RuntimeException ex = thrown()
        ex.message == "KABOOOOMN"

        and:
        0 * emailService._

        where: pathVariable << ["true"]
    }

    void "/api endpoing should return 404 for 'true' pathVariable #pathVariable"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(requestUri + pathVariable))

        then:
        noExceptionThrown()
        0 * emailService._

        and:
        actions.andExpect(MockMvcResultMatchers.status().isBadRequest())

        where: pathVariable << ["true"]
    }


    def "sendMail method works as expected"() {

        when:
        controller.sendMail()

        then:
        noExceptionThrown()

        and:
        1 * emailService.sendEmail()
    }
    void "/api/mail should perform as expected"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .post(mailRequestUri))

        then:
        noExceptionThrown()
        1 * emailService.sendEmail()

        and:
        MvcResult result = actions.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn()
        result.getResponse().getContentAsString() == "Email Sent!"
    }
}
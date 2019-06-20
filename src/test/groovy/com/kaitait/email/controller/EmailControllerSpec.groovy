package com.kaitait.email.controller

import com.kaitait.email.service.EmailService
import org.springframework.test.web.servlet.MockMvc
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
        String response = controller.index(pathVariable)

        then:
        noExceptionThrown()
        0 * emailService._

        and:
        response == expectedResult

        where:
        pathVariable             | expectedResult
        null                     | "Index called!"
        "abc"                    | "Index called!"
        "foo"                    | "Index called!"
        "false"                  | "Index called!"
    }

    @Unroll
    void "/ endpoint should perform as expected for 'non true' pathVariable #pathVariable"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(requestUri + pathVariable))

        then:
        noExceptionThrown()
        0 * emailService._

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())
        actions.andExpect(MockMvcResultMatchers.content().string(expectedResult))

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

    void "/ endpoing should return 404 for 'true' pathVariable #pathVariable"() {

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
    void "/mail should perform as expected"() {

        when:
        def actions = mockMvc.perform(MockMvcRequestBuilders
                .get(mailRequestUri))

        then:
        noExceptionThrown()
        1 * emailService.sendEmail()

        and:
        actions.andExpect(MockMvcResultMatchers.status().isOk())
        actions.andExpect(MockMvcResultMatchers.content().string("Email Sent!"))
    }
}
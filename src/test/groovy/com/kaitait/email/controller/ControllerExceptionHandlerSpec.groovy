package com.kaitait.email.controller


import org.springframework.http.HttpStatus
import spock.lang.Specification

import javax.mail.MessagingException
import javax.servlet.http.HttpServletRequest

class ControllerExceptionHandlerSpec extends Specification {

    ControllerExceptionHandler controllerExceptionHandler
    HttpServletRequest request = Mock()
    MessagingException me
    RuntimeException rte

    void setup() {
        controllerExceptionHandler = new ControllerExceptionHandler()
        request = Mock()
        me = new MessagingException("IAE")
        rte = new RuntimeException("RTE")
    }

    def "MessagingExceptionHandler works as expected"() {
        when:
        def result = controllerExceptionHandler.MessagingExceptionErrorHandler(request, me)

        then:
        result.getStatusCode() == HttpStatus.BAD_REQUEST
        result.getBody() == "Can't send mail"

    }

    def "RuntimeExceptionErrorHandler works as expected"() {
        when:
        def result = controllerExceptionHandler.RuntimeExceptionErrorHandler(request, rte)

        then:
        result.getStatusCode() == HttpStatus.BAD_REQUEST
        result.getBody() == "Something unexpected happened"
    }
}

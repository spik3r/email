package com.kaitait.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class EmailApplicationSpec extends Specification {
    @Autowired (required = false)
    EmailApplication emailApplication

    def "when context is loaded emailApplication is created"() {
        expect: "the emailApplication is created"
        emailApplication
    }
}

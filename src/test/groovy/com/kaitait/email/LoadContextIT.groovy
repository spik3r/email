package com.kaitait.email

import com.kaitait.email.controller.EmailController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class LoadContextIT extends Specification {

	@Autowired(required = false)
	private EmailController controller

	def "when context is loaded then all expected beans are created"() {
		expect: "the EmailController is created"
		controller
	}
}
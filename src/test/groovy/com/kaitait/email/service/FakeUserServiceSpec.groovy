package com.kaitait.email.service


import com.kaitait.email.domain.User
import spock.lang.Specification

class FakeUserServiceSpec extends Specification {

    FakeUserService fakeUserService
    User user
    def setup() {
        fakeUserService = new FakeUserService()
    }

    def "getUser works as expected"() {
        when:
        User result = fakeUserService.getUser()

        then:
        result == fakeUserService.FAKE_USER
    }
}

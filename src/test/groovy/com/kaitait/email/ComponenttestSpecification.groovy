package com.kaitait.email

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootContextLoader
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.cache.CacheManager
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

import javax.inject.Inject

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = [EmailApplication])
@TestPropertySource("classpath:/application-test-service.properties")
class ComponenttestSpecification extends Specification  {

    static final String INDEX_URL = "/"
    static final String MAIL_URL = "/mail"

    @Inject
    WebApplicationContext webApplicationContext

    @Inject
    CacheManager cacheManager

    @Value('${local.server.port}')
    protected int port

    @Inject
    TestRestTemplate restTemplate

}
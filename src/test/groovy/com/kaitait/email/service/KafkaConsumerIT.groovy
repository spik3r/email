package com.kaitait.email.service

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.config.KafkaListenerEndpointRegistry
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.kafka.test.rule.KafkaEmbedded
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

//@SpringBootTest(classes = {Consumer.class})
//@EmbeddedKafka
//@EmbeddedKafka(partitions = 1, controlledShutdown = false,
//        brokerProperties = {"listeners=PLAINTEXT://localhost:3333", "port=3333"})
class KafkaConsumerIT extends KafkaConfigurationSpec {

//    PollingConditions conditions = new PollingConditions(timeout: 3)
//
//    def "should process a message with null payload and do NOT store it in DB"() {
//        when: "the message is sent"
//        sendJsonMessage("registration_topic_1", "abc123", FakeUserService.FAKE_USER as String)
//
//        then: "the message is not present in the DB"
//        conditions.eventually {
//            def updated = getDeliveryZipCode("50735")
//            assert ! updated.isPresent()
//        }
//    }
}

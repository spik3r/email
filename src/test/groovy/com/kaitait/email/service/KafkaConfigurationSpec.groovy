package com.kaitait.email.service

import com.kaitait.email.ComponenttestSpecification
import lombok.extern.slf4j.Slf4j
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringSerializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.SendResult
import org.springframework.kafka.test.rule.KafkaEmbedded
import org.springframework.kafka.test.utils.KafkaTestUtils
import org.springframework.test.context.TestPropertySource
import org.springframework.util.concurrent.ListenableFuture
import spock.util.concurrent.PollingConditions

@Slf4j
@TestPropertySource(properties = ['kafka.servers:${spring.embedded.kafka.brokers}'])
class KafkaConfigurationSpec extends ComponenttestSpecification  {

    Logger LOG = LoggerFactory.getLogger(KafkaConfigurationSpec.class)

    static String TEST_TOPIC_NAME = "test_topic_1"

    PollingConditions conditions = new PollingConditions(timeout: 3, initialDelay: 1)

    static KafkaEmbedded embeddedKafka = new KafkaEmbedded(1, false, 1,
            TEST_TOPIC_NAME)

    def setupSpec() {
        if (embeddedKafka.kafkaServers == null || embeddedKafka.kafkaServers.size() == 0) {
            embeddedKafka.before()
        }
    }

    void sendJsonMessage(String topic, String key, String data) {
        ListenableFuture<SendResult> listenableFuture = getKafkaTemplate().send(topic, key, data)
        kafkaTemplate.flush()
        conditions.eventually {
            assert listenableFuture.isDone()
        }
        LOG.info("message to topic {} send", topic)
    }

    KafkaTemplate<String, String> getKafkaTemplate() {
        Map<String, Object> senderProps = KafkaTestUtils.producerProps(embeddedKafka)
        senderProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName())
        senderProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName())
        ProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(senderProps)
        return new KafkaTemplate<>(producerFactory)
    }
}

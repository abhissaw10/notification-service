package com.bmc.notificationservice.config;

import com.bmc.notificationservice.model.Doctor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Value(value = "${kafka.bootstrap.address}")
    private String bootStrapAddress;

    @Value(value = "${kafka.consumer.groupid}")
    private String groupId;


    /*@Bean
    public ConsumerFactory<String, Doctor> doctorConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
            bootStrapAddress);
        props.put(
            ConsumerConfig.GROUP_ID_CONFIG,
            groupId);
        props.put(
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
            StringDeserializer.class);
        props.put(
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
            JsonDeserializer.class);
        JsonDeserializer jsonDeserializer = new JsonDeserializer<>(Doctor.class, false);
        jsonDeserializer.trustedPackages("*");
        return new DefaultKafkaConsumerFactory<>(
            props,
            new StringDeserializer(),
            jsonDeserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Doctor>
    doctorKafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<String, Doctor> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(doctorConsumerFactory());
        return factory;
    }*/


    @KafkaListener(topics = "${doctor.registration.notification}", groupId = "${kafka.consumer.groupid}")
    public void listenGroupFoo(Message<Doctor> message) {
        System.out.println("Received Message in group foo: " + message.getPayload());
    }



}

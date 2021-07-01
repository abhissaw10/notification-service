package com.bmc.notificationservice.config;

import com.bmc.notificationservice.model.Appointment;
import com.bmc.notificationservice.model.Doctor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class NotificationKafkaListener {

    @Autowired
    private ObjectMapper mapper;

    @KafkaListener(topics = "${doctor.registration.notification}", groupId = "${kafka.consumer.doctor.registration.groupid}",  containerFactory="doctorKafkaListenerContainerFactory")
    public void listenDoctorRegistration(Message<Doctor> message) {
        System.out.println("Received Message in group: " + message.getPayload());
    }

    @KafkaListener(topics = "${appointment.confirmation.notification}", groupId = "${kafka.consumer.appointment.groupid}",  containerFactory="appointmentKafkaListenerContainerFactory")
    public void listenAppointmentConfirmation(Message<String> message) throws JsonProcessingException {

        Appointment appointment = mapper.readValue(message.getPayload(),Appointment.class);
        System.out.println("Received Message in group: " + message.getPayload());
    }

}

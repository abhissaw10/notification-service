package com.bmc.notificationservice.model;

import com.bmc.notificationservice.config.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    private String appointmentId;
    private String doctorId;
    private String userId;
    private String timeSlot;
    private String status;
    private LocalDate appointmentDate;
    private String symptoms;
    private String priorMedicalHistory;

}

package com.segatto.agilitytransports.AgilityTransports.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@Table(name = "schedule_transport")
@Entity
@NoArgsConstructor
public class ScheduleTransportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cod_sign")
    private String signCode;

    @Column(name = "schedule_date")
    private Instant scheduleDate;

}

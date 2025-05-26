package org.electric.zealous_electro_care.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Booking {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long bookingId;
    private String bookedBy;
    private String technician;
    private LocalDateTime scheduledDateTime;
    private int service;
    private String status;// Pending, Confirmed, Completed, Cancelled
}

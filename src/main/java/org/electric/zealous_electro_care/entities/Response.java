package org.electric.zealous_electro_care.entities;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {
    private long bookingId;
    private String bookedBy;
    private String technician;
    private LocalDateTime scheduledDateTime;
    private int service;
    private String status;// Pending, Confirmed, Completed, Cancelled
    private String fullName;
    private long mobile;
}

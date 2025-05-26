package org.electric.zealous_electro_care.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {
    private String bookingBy;
    private Double latitude;
    private Double longitude;
    private int service;
}

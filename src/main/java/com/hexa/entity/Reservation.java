package com.hexa.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    private int reservationId;
    private int assetId;
    private int employeeId;
    private Date reservationDate;
    private Date startDate;
    private Date endDate;
    private String status;

    
}


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

public class MaintenanceRecord {
    private int maintenanceId;
    private int assetId;
    private Date maintenanceDate;
    private String description;
    private double cost;

    
}

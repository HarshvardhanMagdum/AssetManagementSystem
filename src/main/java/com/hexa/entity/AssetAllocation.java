package com.hexa.entity;


import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class AssetAllocation {
    private int allocationId;
    private int assetId;
    private int employeeId;
    private Date allocationDate;
    private Date returnDate;

    
}


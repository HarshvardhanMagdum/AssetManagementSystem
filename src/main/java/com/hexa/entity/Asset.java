package com.hexa.entity;

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
public class Asset {
    private int assetId;
    private String name;
    private String type;
    private String serialNumber;
    private String purchaseDate;
    private String location;
    private String status;
    private int ownerId;

    
}


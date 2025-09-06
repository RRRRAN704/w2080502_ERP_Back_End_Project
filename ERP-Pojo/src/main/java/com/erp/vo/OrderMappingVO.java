package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderMappingVO implements Serializable {

    //order ID
    private Long id;

    //Company Name
    private String companyName;

    //contact person
    private String contactPerson;
    
}


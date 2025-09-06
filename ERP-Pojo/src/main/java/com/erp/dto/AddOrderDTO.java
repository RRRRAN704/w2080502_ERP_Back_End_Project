package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * DTO for generating order
 */
@Data
public class AddOrderDTO implements Serializable {
    private String companyName;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String remark;
}

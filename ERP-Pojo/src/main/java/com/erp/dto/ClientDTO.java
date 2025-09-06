package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * client info
 */
@Data
public class ClientDTO implements Serializable {
    private Long id;
    private Long employeeId;
    private Long departmentId;
    private String companyName;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private Integer isArchived;
    private String remark;
}

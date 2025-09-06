package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * assign order to employee
 */
@Data
public class ChangeOrderStatusDTO implements Serializable {
    private Long id;
    private Long employeeId;
}

package com.erp.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * employee info
 */
@Data
public class EmployeeDTO implements Serializable {
    private Long id;
    private String name;
    private String username;
    private Long positionId;
    private Long departmentId;
    private String phone;
    private String sex;
    private String email;
}

package com.erp.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * contract info
 */
@Data
public class ContractDTO implements Serializable {
    private Long id;
    private String contractName;
    private String contractNumber;
    private Long employeeId;
    private Long clientId;
    private Long departmentId;
    private Integer amount;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private Integer isArchived;
    private String fileUrl;
}

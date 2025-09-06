package com.erp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * contract table data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contract implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private LocalDateTime createTime;
    private Long createUser;
    private LocalDateTime updateTime;
    private Long updateUser;
}


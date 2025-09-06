package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/*
* Client Table Data
* */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long employeeId;
    private Long departmentId;
    private String companyName;
    private String contactPerson;
    private String contactPhone;
    private String contactEmail;
    private String address;
    private Integer isArchived;
    private LocalDateTime createTime;
    private Long createUser;
    private LocalDateTime updateTime;
    private Long updateUser;
    private String remark;

}

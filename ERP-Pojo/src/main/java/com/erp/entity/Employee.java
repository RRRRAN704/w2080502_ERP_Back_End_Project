package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


/*
* Employee Table Data
* */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String username;
    private Long positionId;
    private Long departmentId;
    private String password;
    private String phone;
    private String sex;
    private Integer status;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
    private String email;

}

package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/*
* postion table data
* */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String positionName;
    private Long departmentId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;
}

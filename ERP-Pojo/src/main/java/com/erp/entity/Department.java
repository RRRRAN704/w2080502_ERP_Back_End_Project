package com.erp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * department table data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Department implements Serializable {
private static final long serialVersionUID = 1L;
private Long id;
private String departmentName;
private LocalDateTime createTime;
private LocalDateTime updateTime;
private Long createUser;
private Long updateUser;

}


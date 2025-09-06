package com.erp.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * order table data
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order implements Serializable {
private static final long serialVersionUID = 1L;
private Long id;
private String companyName;
private String contactPerson;
private String contactPhone;
private String contactEmail;
private Long employeeId;
private String remark;
private Integer isArchived;
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime createTime;
@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
private LocalDateTime assignTime;
private LocalDateTime updateTime;
private Long updateUser;
}


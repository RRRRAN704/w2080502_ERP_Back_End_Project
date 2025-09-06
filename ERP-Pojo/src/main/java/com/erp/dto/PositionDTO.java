package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * position info
 */
@Data
public class PositionDTO implements Serializable {
    private Long id;
    private String positionName;
    private Long departmentId;
}

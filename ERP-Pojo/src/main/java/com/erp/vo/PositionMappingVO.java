package com.erp.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PositionMappingVO implements Serializable {

    //position ID
    private Long id;

    //Position Name
    private String positionName;

    private Long departmentId;
    
}


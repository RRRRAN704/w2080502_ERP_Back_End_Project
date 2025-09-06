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
public class ContractMappingVO implements Serializable {

    //contract ID
    private Long id;

    //contract name
    private String contractName;

    //contract number
    private String contractNumber;

    private Long employeeId;
    private Long clientId;
    private Long departmentId;
    
}


package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * contract page query
 */
@Data
public class ContractPageQueryDTO implements Serializable {

    //contract name
    private String contractName;
    //Page number
    private int page;
    //number of records per page
    private int pageSize;


}

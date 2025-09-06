package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * client page query
 */
@Data
public class ClientPageQueryDTO implements Serializable {


    //Company name
    private String companyName;
    //Page number
    private int page;
    //number of records per page
    private int pageSize;


}

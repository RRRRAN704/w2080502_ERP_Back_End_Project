package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class OrderPageQueryDTO implements Serializable {

    //company name
    private String companyName;
    //Page number
    private int page;
    //number of records per page
    private int pageSize;
}

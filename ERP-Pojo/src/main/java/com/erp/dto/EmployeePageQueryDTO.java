package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * employee page query
 */
@Data
public class EmployeePageQueryDTO implements Serializable {

    //employee name
    private String name;

    //Page number
    private int page;

    //number of records per page
    private int pageSize;
}

package com.erp.dto;

import lombok.Data;

import java.io.Serializable;


/**
 * department page query
 */
@Data
public class DepartmentPageQueryDTO implements Serializable {


    //Department name
    private String departmentName;
    //Page number
    private int page;
    //number of records per page
    private int pageSize;


}

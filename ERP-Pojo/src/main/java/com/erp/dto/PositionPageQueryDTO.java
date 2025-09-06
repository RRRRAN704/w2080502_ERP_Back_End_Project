package com.erp.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionPageQueryDTO implements Serializable {


    private String positionName;
    //Page number
    private int page;
    //number of records per page
    private int pageSize;

}

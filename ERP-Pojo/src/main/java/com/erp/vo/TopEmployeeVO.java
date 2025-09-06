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
public class TopEmployeeVO implements Serializable {

    //top 5 employee name
    private String nameList;

    //contract amount list
    private String amountList;

}


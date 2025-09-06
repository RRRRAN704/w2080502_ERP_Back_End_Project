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
public class NumberOfOrderVO implements Serializable {

    //2025-07-11,2025-07-12
    private String dateList;

    //1,3,4
    private String orderNumberList;

}


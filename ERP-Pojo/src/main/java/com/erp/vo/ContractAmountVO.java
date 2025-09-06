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
public class ContractAmountVO implements Serializable {

    //022-10-01,2022-10-02,2022-10-03
    private String dateList;

    //5000.10000,2000
    private String contractAmountList;

}


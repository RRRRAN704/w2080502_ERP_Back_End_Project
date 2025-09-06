package com.erp.service;


import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.result.PageResult;
import com.erp.vo.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


public interface ReportService {


    /**
     * number of contract statistics within a certain period
     * @param begin
     * @param end
     * @return
     */
    NumberOfContractVO getContractNumberStatistics(LocalDate begin, LocalDate end);


    /**
     * get amount of contract within a certain period
     * @param begin
     * @param end
     * @return
     */
    ContractAmountVO getContractAmountStatistics(LocalDate begin, LocalDate end);


    /**
     * get amount of orders within a certain period
     * @param begin
     * @param end
     * @return
     */
    NumberOfOrderVO getOrderNumber(LocalDate begin, LocalDate end);


    /**
     * get top 5 employees with the most contract amount
     * @param begin
     * @param end
     * @return
     */
    TopEmployeeVO getTopEmployee(LocalDate begin, LocalDate end);
}


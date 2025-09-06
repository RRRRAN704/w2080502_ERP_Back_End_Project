package com.erp.controller.employee;


import com.erp.result.Result;
import com.erp.service.ReportService;
import com.erp.vo.ContractAmountVO;
import com.erp.vo.NumberOfContractVO;
import com.erp.vo.NumberOfOrderVO;
import com.erp.vo.TopEmployeeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("employee/report")
@Api(tags = "Data visualisation chart")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * number of contract statistics within a certain period
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/contractNumber")
    @ApiOperation("Number of Contracts Statistics")
    public Result<NumberOfContractVO> contractNumber(
     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
     @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Number of Contracts statistics: begin: {}, end: {}", begin, end);
        return Result.success(reportService.getContractNumberStatistics(begin,end));
    }


    /**
     * get amount of contract within a certain period
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/contractAmount")
    @ApiOperation("Contract Amount Statistics")
    public Result<ContractAmountVO> contractAmount(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Contracts amount statistics: begin: {}, end: {}", begin, end);
        return Result.success(reportService.getContractAmountStatistics(begin,end));
    }


    /**
     * get amount of orders within a certain period
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/orderNumber")
    @ApiOperation("Order Number Statistics")
    public Result<NumberOfOrderVO> orderNumber(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Order count statistics: begin: {}, end: {}", begin, end);
        return Result.success(reportService.getOrderNumber(begin,end));
    }

    /**
     * get top 5 employees with the most contract amount
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/topEmployee")
    @ApiOperation("Top Employee Statistics")
    public Result<TopEmployeeVO> topEmployee(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("Top Employee statistics: begin: {}, end: {}", begin, end);
        return Result.success(reportService.getTopEmployee(begin,end));
    }

}

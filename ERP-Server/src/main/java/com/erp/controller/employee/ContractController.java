package com.erp.controller.employee;

import com.erp.constant.MessageConstant;
import com.erp.context.BaseContext;
import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.dto.ContractDTO;
import com.erp.dto.ContractPageQueryDTO;
import com.erp.exception.CannotBeAdminException;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/contract")
@Api(tags = "Contract related controller")
@Slf4j
public class ContractController {

    @Autowired
    ContractService contractService;


    /**
     * add contract
     * @param contractDTO
     * @return
     */

    @PostMapping
    @ApiOperation("Add Contract")
    public Result addContract(@RequestBody ContractDTO contractDTO) {
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }

        log.info("Add Contract: {}", contractDTO);
        contractService.addContract(contractDTO);
        return Result.success();

    }



    /**
     * Edit clients
     * @param contractDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Edit Contract")
    public Result editContract(@RequestBody ContractDTO contractDTO) {
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        log.info("Edit contract: {}", contractDTO);
        contractService.update(contractDTO);
        return Result.success();

    }


    /**
     * Archive contracts in batch
     * @param ids
     * @return
     */

    @PutMapping("/archive")
    @ApiOperation("Archive contracts in batch")
    public Result archiveBatch(@RequestBody List<Long> ids){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        contractService.archiveBatch(ids);
        return Result.success();
    }


    /**
     * Restore contracts in batch
     * @param ids
     * @return
     */
    @PutMapping("/restore")
    @ApiOperation("Restore contracts in batch")
    public Result restoreBatch(@RequestBody List<Long> ids){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        contractService.restoreBatch(ids);
        return Result.success();
    }


    /**
     * contract page query
     * @param contractPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Contract page query")
    public Result<PageResult> pageClient(ContractPageQueryDTO contractPageQueryDTO){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        log.info("Contract page query {}", contractPageQueryDTO);
        PageResult pageResult = contractService.contractPageQuery(contractPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * archived contract page query
     * @param contractPageQueryDTO
     * @return
     */
    @GetMapping("/page/archived")
    @ApiOperation("Archived Contract page query")
    public Result<PageResult> pageArchivedClient(ContractPageQueryDTO contractPageQueryDTO){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        log.info("Contract page query {}", contractPageQueryDTO);
        PageResult pageResult = contractService.contractArchivedPageQuery(contractPageQueryDTO);
        return Result.success(pageResult);
    }
}

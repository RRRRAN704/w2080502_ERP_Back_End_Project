package com.erp.controller.employee;


import com.erp.constant.MessageConstant;
import com.erp.context.BaseContext;
import com.erp.dto.DepartmentDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.exception.NotAdminException;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.DepartmentService;
import com.erp.service.EmployeeService;
import com.github.pagehelper.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/department")
@Slf4j
@Api(tags = "Department related api")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;



    /**
     * Add new department
     * @param departmentDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new department")
    public Result saveDepartment(@RequestBody DepartmentDTO departmentDTO) {
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }

        log.info("Add new department{}", departmentDTO);
        departmentService.saveDepartment(departmentDTO);
        return Result.success();

    }


    /**
     * department page query
     * @param departmentPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("department page query")
    public Result<PageResult> pageDepartment(DepartmentPageQueryDTO departmentPageQueryDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Department page query {}", departmentPageQueryDTO);
        PageResult pageResult = departmentService.departmentPageQurey(departmentPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * Edit department info
     * @param departmentDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Edit department info")
    public Result updateDepartment(@RequestBody DepartmentDTO departmentDTO) {
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Update department {}", departmentDTO);
        departmentService.updateDepartment(departmentDTO);
        return Result.success();

    }


    /**
     * delete department in batch
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete department in batch")
    public Result deleteDepartment(@RequestParam List<Long> ids){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Delete department in batch {}", ids);
        departmentService.deleteBatch(ids);
        return Result.success();
    }




}

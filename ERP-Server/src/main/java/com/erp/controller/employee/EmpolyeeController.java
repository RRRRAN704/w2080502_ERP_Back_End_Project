package com.erp.controller.employee;


import com.erp.constant.JwtClaimsConstant;
import com.erp.constant.MessageConstant;
import com.erp.context.BaseContext;
import com.erp.dto.*;
import com.erp.entity.Employee;
import com.erp.exception.NotAdminException;
import com.erp.properties.JwtProperties;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.EmailService;
import com.erp.service.EmployeeService;
import com.erp.utils.JwtUtil;
import com.erp.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/employee/info")
@Slf4j
@Api(tags = "Employee related api")
public class EmpolyeeController {

@Autowired
private EmployeeService employeeService;

@Autowired
private JwtProperties jwtProperties;

@Autowired
private EmailService emailService;


/*
* login function
* */
    @PostMapping("/login")
    @ApiOperation(value = "Employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("Employee login");

        Employee employee = employeeService.login(employeeLoginDTO);

        String position = employeeService.getPositionById(employee.getPositionId());

        Map<String,Object> claims = new HashedMap<>();
        claims.put(JwtClaimsConstant.POSITION,position);
        claims.put(JwtClaimsConstant.EMP_ID,employee.getId());
        claims.put(JwtClaimsConstant.DEPARTMENT_ID,employee.getDepartmentId());

        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims
        );

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .username(employee.getUsername())
                .name(employee.getName())
                .position(position)
                .departmentId(employee.getDepartmentId())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }


    /**
     * logout
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("Employee Logout")
    public Result<String> logout(){
        return Result.success();
    }


    /**
     * add new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new employee")
    public Result saveEmployee(@RequestBody EmployeeDTO employeeDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Add new employee: {}", employeeDTO);
        employeeService.saveEmployee(employeeDTO);
        return Result.success();

    }


    /**
     * employee page query
     * @param employeePageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Employee page query")
    public Result<PageResult> pageEmployee(EmployeePageQueryDTO employeePageQueryDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Employee page query: {}", employeePageQueryDTO);
        PageResult pageResult = employeeService.employeePageQuery(employeePageQueryDTO);
        return Result.success(pageResult);

    }


    /**
     * change employee status
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("Enable or disable employee account")
    public Result startOrStop(@PathVariable Integer status, Long id) {
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Enable or disable employee account: {},{}", status, id);
        employeeService.startOrStop(status,id);
        return Result.success();
    }


    /**
     * edit employee info
     * @param employeeDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Edit employee info")
    public Result update(@RequestBody EmployeeDTO employeeDTO) {
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Edit employee info{}", employeeDTO);
        employeeService.update(employeeDTO);
        return Result.success();
    }


    /**
     * Search employee by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("Search employee by ID")
    public Result<Employee> getById(@PathVariable Long id) {
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        Employee employee = employeeService.getById(id);
        return Result.success(employee);
    }



    /**
     * send email verfication code
     * @param sendEmailCodeDTO
     * @return
     */
    @PostMapping("/send-email-code")
    @ApiOperation("send verification code")
    public Result<String> sendEmailCode(@Valid @RequestBody SendEmailCodeDTO sendEmailCodeDTO) {
        try {
            // check whether email exist in the database
            boolean exists = employeeService.existsByEmail(sendEmailCodeDTO.getEmail());
            if (!exists) {
                return Result.error("Cannot find employee");
            }

            // send verfication code via email
            boolean success = emailService.sendResetPasswordCode(sendEmailCodeDTO.getEmail());

            if (success) {
                return Result.success("The verification code has been sent to your email address. Please check your inbox");
            } else {
                return Result.error("Verification code sent failed, please try again later.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("System error, please try again later.");
        }
    }

    /**
     * Reset password using the email verification code
     * @param resetPasswordDTO
     * @return
     */
    @PostMapping("/reset-password")
    @ApiOperation("reset password")
    public Result<String> resetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        try {
            // Verify that the new password and confirmation password match
            if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getConfirmPassword())) {
                return Result.error("The new password and confirmation password do not match.");
            }

            // Verify email verification code
            boolean codeValid = emailService.verifyEmailCode(resetPasswordDTO.getEmail(), resetPasswordDTO.getVerifyCode());
            if (!codeValid) {
                return Result.error("Verification code error or expired");
            }

            // reset password
            boolean success = employeeService.resetPasswordByEmail(resetPasswordDTO);

            if (success) {
                return Result.success("Password reset successful. Please log in with your new password.");
            } else {
                return Result.error("Password reset failed. Please try again later.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("System error, please try again lat");
        }
    }
}




package com.erp.service;

import com.erp.dto.*;
import com.erp.entity.Employee;
import com.erp.result.PageResult;
import com.erp.vo.EmployeeMappingVO;

import java.util.List;

public interface EmployeeService {


    /**
     * employee login
     *
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * get position name by positionID
     *
     * @param positionId
     * @return
     */
    String getPositionById(Long positionId);


    /**
     * add new employee
     * @param employeeDTO
     */
    void saveEmployee(EmployeeDTO employeeDTO);


    /**
     * employee page query
     * @param employeePageQueryDTO
     * @return
     */
    PageResult employeePageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * change employee status
     * @param status
     * @param id
     */
    void startOrStop(Integer status, Long id);


    /**
     * edit employee info
     * @param employeeDTO
     */
    void update(EmployeeDTO employeeDTO);


    /**
     * get employee info by id
     * @param id
     * @return
     */
    Employee getById(Long id);


    /**
     * check if the email exist in the database
     * @param email
     * @return
     */
    boolean existsByEmail(String email);

    /**
     * reset password
     * @param resetPasswordDTO
     * @return
     */
    boolean resetPasswordByEmail(ResetPasswordDTO resetPasswordDTO);


    /**
     * get employee list
     * @return
     */
    List<EmployeeMappingVO> getAllEmployee();
}

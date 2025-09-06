package com.erp.service.impl;

import com.erp.constant.MessageConstant;
import com.erp.constant.PasswordConstant;
import com.erp.constant.StatusConstant;
import com.erp.dto.*;
import com.erp.entity.Department;
import com.erp.entity.Employee;
import com.erp.exception.AccountLockedException;
import com.erp.exception.AccountNotFoundException;
import com.erp.exception.CodesDoNotMatchException;
import com.erp.exception.PasswordErrorException;
import com.erp.mapper.EmployeeMapper;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.EmployeeService;
import com.erp.vo.EmployeeMappingVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    private final String CODE_PREFIX = "code:"; // Redis Key

    /**
     * @param employeeLoginDTO
     * @return
     */
    @Override
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();


        //get user details by username
        Employee employee = employeeMapper.getByUsername(username);

        //handle exceptions
        if (employee == null) {
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //check passoord and conduct md5 encryption
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //if account is locked throw exception
        if (employee.getStatus() == StatusConstant.DISABLE) {
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        return employee;
    }


    /**
     * get position name by id
     *
     * @param positionId
     * @return
     */
    @Override
    public String getPositionById(Long positionId) {
        String position = employeeMapper.getPositionById(positionId);
        return position;
    }


    /**
     * add new employee
     *
     * @param employeeDTO
     */
    @Override
    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setStatus(StatusConstant.ENABLE);
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        employeeMapper.insert(employee);
    }


    /**
     * employee page query
     *
     * @param employeePageQueryDTO
     * @return
     */
    @Override
    public PageResult employeePageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        long total = page.getTotal();
        List<Employee> records = page.getResult();
        return new PageResult(total, records);
    }


    /**
     * change employee account status
     *
     * @param status
     * @param id
     */
    @Override
    public void startOrStop(Integer status, Long id) {
        Employee employee = Employee.builder().
                status(status).
                id(id).
                build();
        employeeMapper.update(employee);
    }


    /**
     * edit employee info
     *
     * @param employeeDTO
     */
    @Override
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employeeMapper.update(employee);
    }


    /**
     * get employee info by id
     *
     * @param id
     * @return
     */
    @Override
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("****");
        return employee;
    }


    /**
     * whether the current email exixst in the database
     * @param email
     * @return
     */
    @Override
    public boolean existsByEmail(String email) {
        try {
            Employee employee = employeeMapper.selectByEmail(email);
            return employee != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * reset passoword
     * @param resetPasswordDTO
     * @return
     */
    @Override
    public boolean resetPasswordByEmail(ResetPasswordDTO resetPasswordDTO) {
        try {
            // check if the employee exists
            Employee employee = employeeMapper.selectByEmail(resetPasswordDTO.getEmail());

            if (employee == null) {
                log.warn("Employee does not exist：{}", resetPasswordDTO.getEmail());
                return false;
            }

            // encrypt new passwords
            String encryptedNewPassword = DigestUtils.md5DigestAsHex(resetPasswordDTO.getNewPassword().getBytes());

            // update new password
            Employee updateEmployee = new Employee();
            updateEmployee.setId(employee.getId());
            updateEmployee.setPassword(encryptedNewPassword);

            int result = employeeMapper.updateById(updateEmployee);

            if (result > 0) {
                log.info("Employee password reset successful, email：{}", resetPasswordDTO.getEmail());
                return true;
            } else {
                log.error("Employee password reset failed, email：{}", resetPasswordDTO.getEmail());
                return false;
            }

        } catch (Exception e) {
            log.error("Password reset error, email: {}, error message: {}", resetPasswordDTO.getEmail(), e.getMessage());
            return false;
        }
    }


    /**
     * get employee list
     * @return
     */
    @Override
    public List<EmployeeMappingVO> getAllEmployee() {
        List <Employee> list = employeeMapper.getAllEmployee();
        List <EmployeeMappingVO> employeeMappingVOList = new ArrayList<>();

       for (Employee employee : list) {
           EmployeeMappingVO employeeMappingVO = new EmployeeMappingVO();
           BeanUtils.copyProperties(employee, employeeMappingVO);
           employeeMappingVOList.add(employeeMappingVO);
       }
       return employeeMappingVOList;
    }


}



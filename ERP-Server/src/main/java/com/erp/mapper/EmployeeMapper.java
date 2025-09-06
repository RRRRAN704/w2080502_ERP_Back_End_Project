package com.erp.mapper;

import com.erp.annotation.AutoFill;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.dto.EmployeePageQueryDTO;
import com.erp.entity.Department;
import com.erp.entity.Employee;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {

    //get employee by ID
    @Select("select * from employee where username =#{username}")
    Employee getByUsername(String username);

   //get position name by id
    @Select("select position_name from position where id =#{positionId}" )
    String getPositionById(Long positionId);


    /**
     * insert new employee entry
     * @param employee
     */

    @Insert("insert into employee (name,username,position_id, department_id, password, phone, sex, status, " +
            "create_time, update_time, create_user, update_user, email) "
            + "values"
            + "(#{name},#{username},#{positionId},#{departmentId},#{password},#{phone},#{sex},#{status}," +
            "#{createTime},#{updateTime},#{createUser},#{updateUser},#{email})")
    @AutoFill(value = OperationType.INSERT)
    void insert(Employee employee);

    /**
     * employee page query
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> pageQuery(EmployeePageQueryDTO employeePageQueryDTO);


    /**
     * change employee account status
     * update employee
     * @param employee
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);


    /**
     * get employee info by ID
     * @param id
     * @return
     */
    @Select("select * from employee where id = #{id}")
    Employee getById(Long id);


    /**
     * retrive employee by email
     * @param email
     * @return
     */
    Employee selectByEmail(@Param("email") String email);

    /**
     * update employee by ID
     * @param employee
     * @return
     */
    @AutoFill(value = OperationType.UPDATE)
    int updateById(Employee employee);

   /**
    * get all employee id and name
    * @return
    */
   @Select("select id, name,position_id,department_id from employee")
   List<Employee> getAllEmployee();
  }

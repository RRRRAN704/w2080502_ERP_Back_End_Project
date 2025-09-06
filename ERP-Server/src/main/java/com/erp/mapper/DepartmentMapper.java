package com.erp.mapper;


import com.erp.annotation.AutoFill;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.entity.Department;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepartmentMapper {


    //insert new department
    @AutoFill(value = OperationType.INSERT)
    void insertDepartment(Department department);

    /**
     * department page query
     * @param departmentPageQueryDTO
     * @return
     */
    Page<Department> departmentPageQuery(DepartmentPageQueryDTO departmentPageQueryDTO);


    /**
     * update department info
     * @param department
     */
    @AutoFill(value = OperationType.UPDATE)
    void updateDepartment(Department department);


    /**
     * delete by department id
     * @param id
     */
    @Delete("delete from department where id=#{id}")
    void deleteById(Long id);


    /**
     * get department id and name list
     * @return
     */
    @Select("select id, department_name from department")
    List<Department> getAllDepartment();
}

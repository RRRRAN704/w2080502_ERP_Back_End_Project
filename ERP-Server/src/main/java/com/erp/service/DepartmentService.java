package com.erp.service;


import com.erp.dto.DepartmentDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.result.PageResult;
import com.erp.vo.DepartmentMappingVO;

import java.util.List;

public interface DepartmentService {


    //save a new department info
    void saveDepartment(DepartmentDTO departmentDTO);


    /**
     * department page query
     * @param departmentPageQueryDTO
     * @return
     */
    PageResult departmentPageQurey(DepartmentPageQueryDTO departmentPageQueryDTO);


    /**
     * update department
     * @param departmentDTO
     */
    void updateDepartment(DepartmentDTO departmentDTO);


    /**
     * delete department in batch
     * @param ids
     */
    void deleteBatch(List<Long> ids);


    /**
     * get department mapping list
     * @return
     */
    List<DepartmentMappingVO> getAllDepartment();
}


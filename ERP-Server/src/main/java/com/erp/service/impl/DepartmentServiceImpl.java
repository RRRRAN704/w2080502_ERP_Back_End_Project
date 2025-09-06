package com.erp.service.impl;

import com.erp.dto.DepartmentDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.entity.Department;
import com.erp.mapper.DepartmentMapper;
import com.erp.result.PageResult;
import com.erp.service.DepartmentService;
import com.erp.vo.DepartmentMappingVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * save new department Info
     * @param departmentDTO
     */
    @Override
    public void saveDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        departmentMapper.insertDepartment(department);

    }

    /**
     * Department page query
     * @param departmentPageQueryDTO
     * @return
     */
    @Override
    public PageResult departmentPageQurey(DepartmentPageQueryDTO departmentPageQueryDTO) {

        //start page query
        PageHelper.startPage(departmentPageQueryDTO.getPage(),departmentPageQueryDTO.getPageSize());
        Page<Department> department = departmentMapper.departmentPageQuery(departmentPageQueryDTO);
        long total = department.getTotal();
        List<Department> records = department.getResult();
        return new PageResult(total,records);
    }


    /**
     * update department name
     * @param departmentDTO
     */
    @Override
    public void updateDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        BeanUtils.copyProperties(departmentDTO, department);
        departmentMapper.updateDepartment(department);
    }


    /**
     *delete departments
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        for(Long id: ids){
            departmentMapper.deleteById(id);
        }
    }


    /**
     * get department list
     * @return
     */
    @Override
    public List<DepartmentMappingVO> getAllDepartment() {
        List <Department> list = departmentMapper.getAllDepartment();
        List<DepartmentMappingVO> departmentMappingVOList = new ArrayList<>();
        for (Department department : list) {
            DepartmentMappingVO departmentMappingVO = new DepartmentMappingVO();
            BeanUtils.copyProperties(department, departmentMappingVO);
            departmentMappingVOList.add(departmentMappingVO);
        }
        return departmentMappingVOList;
    }

}

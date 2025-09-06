package com.erp.mapper;


import com.erp.annotation.AutoFill;
import com.erp.dto.ContractPageQueryDTO;
import com.erp.entity.Contract;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface ContractMapper {


    /**
     * insert contract
     * @param contract
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Contract contract);

    /**
     * update contract
     * @param contract
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Contract contract);


    /**
     *manager page query, manager can view all contracts of his own department
     * @param contractPageQueryDTO
     * @param departmentId
     * @return
     */
    Page<Contract> managerPageQuery(ContractPageQueryDTO contractPageQueryDTO, Long departmentId);


    /**
     * consultant page query, consultant can only view their own contracts
     * @param contractPageQueryDTO
     * @param employeeId
     * @return
     */
    Page<Contract> consultantPageQuery(ContractPageQueryDTO contractPageQueryDTO, Long employeeId);

    /**
     *get contract id and company name, contract number mapping
     * @return
     */
    @Select("select id, contract_name, contract_number,employee_id,client_id,department_id from contract")
    List<Contract> getAllContract();


    /**
     * manger can view archived page of the whole department
     * @param contractPageQueryDTO
     * @param departmentId
     * @return
     */
    Page<Contract> managerArchivedPageQuery(ContractPageQueryDTO contractPageQueryDTO, Long departmentId);


    /**
     * consultant can only view their own archived pages
     * @param contractPageQueryDTO
     * @param employeeId
     * @return
     */
    Page<Contract> consultantArchivedPageQuery(ContractPageQueryDTO contractPageQueryDTO, Long employeeId);

    /**
     * contract count statistics
     * @param map
     * @return
     */
    Integer countByMap(Map map);


    /**
     * contract amount statistics
     * @param map
     * @return
     */
    Integer sumByMap(Map map);


    /**
     * get top 5 employee with most contract value
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> getTopEmployeeByContractAmount (@Param("beginTime") LocalDateTime beginTime,
                                                              @Param("endTime")LocalDateTime endTime);
}

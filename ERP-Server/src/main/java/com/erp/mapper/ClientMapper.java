package com.erp.mapper;


import com.erp.annotation.AutoFill;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.entity.Client;
import com.erp.entity.Department;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClientMapper {

    /**
     * insert new client
     * @param client
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Client client);

    /**
     * update client
     * @param client
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Client client);

    /**
     * manager page query, manager can view all clients of his own department
     * @param clientPageQueryDTO
     * @param departmentId
     * @return
     */
    Page<Client> managerPageQuery(ClientPageQueryDTO clientPageQueryDTO, Long departmentId);


    /**
     * consultant page query, consultant can only view their own client
     * @param clientPageQueryDTO
     * @param employeeId
     * @return
     */
    Page<Client> consultantPageQuery(ClientPageQueryDTO clientPageQueryDTO, Long employeeId);


    /**
     * get client list
     * @return
     */

    @Select("select id, company_name, contact_person, employee_id, department_id from client")
    List<Client> getAllClient();


    /**
     * manger can view archived clients of the whole department
     * @param clientPageQueryDTO
     * @param departmentId
     * @return
     */
    Page<Client> managerArchivedPageQuery(ClientPageQueryDTO clientPageQueryDTO, Long departmentId);


    /**
     * consultant can only view their own archived clients
     * @param clientPageQueryDTO
     * @param employeeId
     * @return
     */
    Page<Client> consultantArchivedPageQuery(ClientPageQueryDTO clientPageQueryDTO, Long employeeId);
}

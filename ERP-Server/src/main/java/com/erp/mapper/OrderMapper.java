package com.erp.mapper;


import com.erp.annotation.AutoFill;
import com.erp.dto.OrderPageQueryDTO;
import com.erp.entity.Order;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.Map;


@Mapper
public interface OrderMapper {

    /**
     * archive/unarchive order
     * @param order
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Order order);


    /**
     * manager page query
     * @param orderPageQueryDTO
     * @return
     */

    Page<Order> managerPageQuery(OrderPageQueryDTO orderPageQueryDTO);



    /**
     * consultant page query
     * @param orderPageQueryDTO
     * @return
     */

    Page<Order> consultantPageQuery(OrderPageQueryDTO orderPageQueryDTO, Long employeeId);

    /**
     * manager can view all archived order of his own department
     * @param orderPageQueryDTO
     * @return
     */
    Page<Order> managerArchivedPageQuery(OrderPageQueryDTO orderPageQueryDTO);


    /**
     * consultant can only view archived orders of their own client
     * @param orderPageQueryDTO
     * @param employeeId
     * @return
     */
    Page<Order> consultantArchivedPageQuery(OrderPageQueryDTO orderPageQueryDTO, Long employeeId);

    /**
     * add order
     * @param order
     */
    void insert(Order order);


    /**
     * change order status
     * @param order
     */
    @AutoFill(value = OperationType.UPDATE)
    void assignOrder(Order order);


    /**
     * order count statistics
     * @param map
     * @return
     */
    Integer countByMap(Map map);
}

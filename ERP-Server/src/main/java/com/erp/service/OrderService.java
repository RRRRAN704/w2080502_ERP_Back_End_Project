package com.erp.service;

import com.erp.dto.AddOrderDTO;
import com.erp.dto.ChangeOrderStatusDTO;
import com.erp.dto.OrderDTO;
import com.erp.dto.OrderPageQueryDTO;
import com.erp.result.PageResult;

import java.util.List;

public interface OrderService {
//    /**
//     * Archive order
//     * @param orderDTO
//     */
//    void update(OrderDTO orderDTO);


    /**
     * archive orders in batch
     * @param ids
     */
    void archiveBatch(List<Long> ids);



    /**
     * restore orders in batch
     * @param ids
     */
    void restoreBatch(List<Long> ids);


    /**
     * order page query
     * @param orderPageQueryDTO
     * @return
     */
    PageResult orderPageQurey(OrderPageQueryDTO orderPageQueryDTO);


    /**
     * archived order page query
     * @param orderPageQueryDTO
     * @return
     */
    PageResult orderArchivedPageQurey(OrderPageQueryDTO orderPageQueryDTO);


    /**
     * add order
     * @param addOrderDTO
     */
    void addOrder(AddOrderDTO addOrderDTO);


    /**
     * change order status
     * @param changeOrderStatusDTO
     */
    void assignOrder(ChangeOrderStatusDTO changeOrderStatusDTO);
}

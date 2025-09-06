package com.erp.service.impl;

import com.erp.context.BaseContext;
import com.erp.dto.AddOrderDTO;
import com.erp.dto.ChangeOrderStatusDTO;
import com.erp.dto.OrderDTO;
import com.erp.dto.OrderPageQueryDTO;
import com.erp.entity.Department;
import com.erp.entity.Order;
import com.erp.mapper.OrderMapper;
import com.erp.result.PageResult;
import com.erp.service.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrdereServiceImpl implements OrderService {


    @Autowired
    OrderMapper orderMapper;

//    /**
//     *archive order/unarchive
//     * @param orderDTO
//     */
//    @Override
//    public void update(OrderDTO orderDTO) {
//        Order order = new Order();
//        BeanUtils.copyProperties(orderDTO, order);
//        orderMapper.update(order);
//    }


    /**
     * archive orders in batch
     * @param ids
     */
    @Override
    public void archiveBatch(List<Long> ids) {
        for (Long id : ids) {
            Order order = new Order();
            order.setId(id);
            order.setIsArchived(1);
            orderMapper.update(order);
        }
    }


    /**
     * restore orders in batch
     * @param ids
     */
    @Override
    public void restoreBatch(List<Long> ids) {
        for (Long id : ids) {
            Order order = new Order();
            order.setId(id);
            order.setIsArchived(0);
            orderMapper.update(order);
        }
    }


    /**
     * order page query
     * @param orderPageQueryDTO
     * @return
     */
    @Override
    public PageResult orderPageQurey(OrderPageQueryDTO orderPageQueryDTO) {

        PageHelper.startPage(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());

        if(BaseContext.getCurrentRole().equals("Manager")){
            Page<Order> order = orderMapper.managerPageQuery(orderPageQueryDTO);
            long total = order.getTotal();
            List<Order> records = order.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Order> order = orderMapper.consultantPageQuery(orderPageQueryDTO, employeeId);
            long total = order.getTotal();
            List<Order> records = order.getResult();
            return new PageResult(total,records);
        }

    }


    /**
     * archived order page query
     * @param orderPageQueryDTO
     * @return
     */
    @Override
    public PageResult orderArchivedPageQurey(OrderPageQueryDTO orderPageQueryDTO) {
        PageHelper.startPage(orderPageQueryDTO.getPage(),orderPageQueryDTO.getPageSize());

        if(BaseContext.getCurrentRole().equals("Manager")){
            Page<Order> order = orderMapper.managerArchivedPageQuery(orderPageQueryDTO);
            long total = order.getTotal();
            List<Order> records = order.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Order> order = orderMapper.consultantArchivedPageQuery(orderPageQueryDTO, employeeId);
            long total = order.getTotal();
            List<Order> records = order.getResult();
            return new PageResult(total,records);
        }
    }


    /**
     * generate order from client's end
     * @param addOrderDTO
     */
    @Override
    public void addOrder(AddOrderDTO addOrderDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(addOrderDTO, order);
        order.setCreateTime(LocalDateTime.now());
        orderMapper.insert(order);

    }


    /**
     * assign order
     * @param changeOrderStatusDTO
     */
    @Override
    public void assignOrder(ChangeOrderStatusDTO changeOrderStatusDTO) {
        Order order = new Order();
        BeanUtils.copyProperties(changeOrderStatusDTO, order);
        order.setAssignTime(LocalDateTime.now());
        orderMapper.assignOrder(order);
    }
}

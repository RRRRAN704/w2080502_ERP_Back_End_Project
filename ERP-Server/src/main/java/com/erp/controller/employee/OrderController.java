package com.erp.controller.employee;


import com.erp.constant.MessageConstant;
import com.erp.context.BaseContext;
import com.erp.dto.*;
import com.erp.exception.CannotBeAdminException;
import com.erp.exception.NoAccessException;
import com.erp.exception.NotAdminException;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.expression.spel.ast.Assign;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/order")
@Slf4j
@Api(tags = "Order related api")
public class OrderController {


    @Autowired
    OrderService orderService;

    /**
     * add order
     * @param addOrderDTO
     * @return
     */
    @PostMapping
    @ApiOperation("generate request from client end")
    public Result addOrder(@RequestBody AddOrderDTO addOrderDTO) {
        log.info("Add Order: {}", addOrderDTO );
        orderService.addOrder(addOrderDTO);
        return Result.success();
    }


    /**
     * Assign order to employee
     * @param changeOrderStatusDTO
     * @return
     */
    @PutMapping("/assign")
    @ApiOperation("Assign order")
    public Result assignOrder(@RequestBody ChangeOrderStatusDTO changeOrderStatusDTO) {
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }


       if (!BaseContext.getCurrentRole().equals("Manager")){
         throw new NoAccessException(MessageConstant.MANAGER_ONLY);
       }


        log.info("Assign order: {}", changeOrderStatusDTO );
        orderService.assignOrder(changeOrderStatusDTO);
        return Result.success();

    }


    /**
     * Archive order in batch
     * @param ids
     * @return
     */

    @PutMapping("/archive")
    @ApiOperation("Archive order in batch")
    public Result archiveBatch(@RequestBody List<Long> ids){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        orderService.archiveBatch(ids);
        return Result.success();
    }


    /**
     * Restore order in batch
     * @param ids
     * @return
     */
    @PutMapping("/restore")
    @ApiOperation("Restore order in batch")
    public Result restoreBatch(@RequestBody List<Long> ids){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        orderService.restoreBatch(ids);
        return Result.success();
    }


    /**
     * order page query
     * @param orderPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("order page query")
    public Result<PageResult> pageOrder(OrderPageQueryDTO orderPageQueryDTO){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        log.info("Order page query {}", orderPageQueryDTO);
        PageResult pageResult = orderService.orderPageQurey(orderPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * order page query
     * @param orderPageQueryDTO
     * @return
     */
    @GetMapping("/page/archived")
    @ApiOperation("Archived order page query")
    public Result<PageResult> pageArchivedOrder(OrderPageQueryDTO orderPageQueryDTO){
        if (BaseContext.getCurrentRole().equals("System Admin")) {
            throw new CannotBeAdminException(MessageConstant.ADMIN_NO_ACCESS);
        }
        log.info("Order page query {}", orderPageQueryDTO);
        PageResult pageResult = orderService.orderArchivedPageQurey(orderPageQueryDTO);
        return Result.success(pageResult);
    }
}

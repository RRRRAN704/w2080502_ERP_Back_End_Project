package com.erp.controller.employee;


import com.erp.constant.MessageConstant;
import com.erp.context.BaseContext;
import com.erp.dto.PositionDTO;
import com.erp.dto.PositionPageQueryDTO;
import com.erp.exception.NotAdminException;
import com.erp.result.PageResult;
import com.erp.result.Result;
import com.erp.service.PositionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee/position")
@Slf4j
@Api(tags = "Position related api")
public class PositionController {

    @Autowired
    PositionService positionService;


    /**
     * add new position
     * @param positionDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new position")
    public Result savePosition(@RequestBody PositionDTO positionDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Add new position: {}", positionDTO);
        positionService.savePosition(positionDTO);
        return Result.success();
    }

    /**
     * position page query
     * @param positionPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("Position page query")
    public Result<PageResult> pagePosition(PositionPageQueryDTO positionPageQueryDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Position Page Query: {}", positionPageQueryDTO);
        PageResult pageResult = positionService.positionPageQuery(positionPageQueryDTO);
        return Result.success(pageResult);
    }


    /**
     * update position detail
     * @param positionDTO
     * @return
     */
    @PutMapping
    @ApiOperation("Edit position info")
    public Result updatePosition(@RequestBody PositionDTO positionDTO){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Edit position: {}", positionDTO);
        positionService.updatePosition(positionDTO);
        return Result.success();
    }


    /**
     *  delete position in batch
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("Delete position in batch")
    public Result deletePosition(@RequestParam List<Long> ids){
        if (!BaseContext.getCurrentRole().equals("System Admin")) {
            throw new NotAdminException(MessageConstant.NOT_ADMIN);
        }
        log.info("Delete position ids are: {}", ids);
        positionService.deleteBatch(ids);
        return Result.success();
    }




}

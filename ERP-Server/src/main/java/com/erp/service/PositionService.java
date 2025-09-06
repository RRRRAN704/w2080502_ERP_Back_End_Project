package com.erp.service;

import com.erp.dto.PositionDTO;
import com.erp.dto.PositionPageQueryDTO;
import com.erp.result.PageResult;
import com.erp.vo.PositionMappingVO;

import java.util.List;

public interface PositionService {

    /**
     * add new position
     * @param positionDTO
     */
    void savePosition(PositionDTO positionDTO);


    /**
     * position page query
     * @param positionPageQueryDTO
     * @return
     */
    PageResult positionPageQuery(PositionPageQueryDTO positionPageQueryDTO);


    /**
     * update position
     * @param positionDTO
     */
    void updatePosition(PositionDTO positionDTO);


    /**
     * delete position in batch
     * @param ids
     */
    void deleteBatch(List<Long> ids);


    /**
     * get postion mapping list
     * @return
     */
    List<PositionMappingVO> getAllPosition();
}

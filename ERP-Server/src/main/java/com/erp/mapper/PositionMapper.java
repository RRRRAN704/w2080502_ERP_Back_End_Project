package com.erp.mapper;


import com.erp.annotation.AutoFill;
import com.erp.dto.PositionPageQueryDTO;
import com.erp.entity.Position;
import com.erp.enumeration.OperationType;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PositionMapper {

    /**
     * insert new position
     * @param position
     */
    @AutoFill(value = OperationType.INSERT)
    void insertPosition(Position position);


    /**
     * position page query
     * @param positionPageQueryDTO
     * @return
     */
    Page<Position> positionPageQuery(PositionPageQueryDTO positionPageQueryDTO);


    /**
     * update position
     * @param position
     */
    @AutoFill(value = OperationType.UPDATE)
    void updatePosition(Position position);


    /**
     * delete position by id
     * @param id
     */
    @Delete("delete from position where id=#{id}")
    void deleteById(Long id);


    /**
     * get position list
     * @return
     */

    @Select("select id, position_name, department_id from position")
    List<Position> getAllPosition();
}

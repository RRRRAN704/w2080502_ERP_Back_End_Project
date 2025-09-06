package com.erp.service.impl;

import com.erp.dto.PositionDTO;
import com.erp.dto.PositionPageQueryDTO;
import com.erp.entity.Position;
import com.erp.mapper.PositionMapper;
import com.erp.result.PageResult;
import com.erp.service.PositionService;
import com.erp.vo.PositionMappingVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    PositionMapper positionMapper;
    /**
     * add position
     * @param positionDTO
     */
    @Override
    public void savePosition(PositionDTO positionDTO) {
        Position position = new Position();
        BeanUtils.copyProperties(positionDTO, position);
        positionMapper.insertPosition(position);

    }


    /**
     * position page query
     * @param positionPageQueryDTO
     * @return
     */
    @Override
    public PageResult positionPageQuery(PositionPageQueryDTO positionPageQueryDTO) {
        PageHelper.startPage(positionPageQueryDTO.getPage(),positionPageQueryDTO.getPageSize());
        Page<Position> position = positionMapper.positionPageQuery(positionPageQueryDTO);
        long total = position.getTotal();
        List<Position> records = position.getResult();
        return new PageResult(total,records);
    }


    /**
     * update position
     * @param positionDTO
     */
    @Override
    public void updatePosition(PositionDTO positionDTO) {
        Position position = new Position();
        BeanUtils.copyProperties(positionDTO, position);
        positionMapper.updatePosition(position);

    }


    /**
     * delete position in batch
     * @param ids
     */
    @Override
    public void deleteBatch(List<Long> ids) {
        for (Long id : ids) {
            positionMapper.deleteById(id);
        }

    }


    /**
     * get position list
     * @return
     */
    @Override
    public List<PositionMappingVO> getAllPosition() {
       List <Position> list = positionMapper.getAllPosition();
       List<PositionMappingVO> positionMappingVOList = new ArrayList<>();
       for (Position position : list) {
           PositionMappingVO positionMappingVO = new PositionMappingVO();
           BeanUtils.copyProperties(position, positionMappingVO);
           positionMappingVOList.add(positionMappingVO);
       }
       return positionMappingVOList;
    }
}

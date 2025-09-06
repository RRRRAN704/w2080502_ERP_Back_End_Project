package com.erp.service;


import com.erp.dto.ContractDTO;
import com.erp.dto.ContractPageQueryDTO;
import com.erp.result.PageResult;
import com.erp.vo.ContractMappingVO;

import java.util.List;

public interface ContractService {


    /**
     * add contracts
     * @param contractDTO
     */
    void addContract(ContractDTO contractDTO);


    /**
     * update contracts
     * @param contractDTO
     */
    void update(ContractDTO contractDTO);


    /**
     * archive contracts
     * @param ids
     */
    void archiveBatch(List<Long> ids);


    /**
     * restore contracts
     * @param ids
     */
    void restoreBatch(List<Long> ids);


    /**
     * contract page query
     * @param contractPageQueryDTO
     * @return
     */
    PageResult contractPageQuery(ContractPageQueryDTO contractPageQueryDTO);


    /**
     * get contract mapping list
     * @return
     */
    List<ContractMappingVO> getAllContract();

    /**
     * archived contract page query
     * @param contractPageQueryDTO
     * @return
     */
    PageResult contractArchivedPageQuery(ContractPageQueryDTO contractPageQueryDTO);
}


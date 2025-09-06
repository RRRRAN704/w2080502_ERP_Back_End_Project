package com.erp.service;


import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.dto.DepartmentDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.result.PageResult;
import com.erp.vo.ClientMappingVO;

import java.util.List;

public interface ClientService {


    /**
     * add client
     * @param clientDTO
     */
    void addClient(ClientDTO clientDTO);


    /**
     * update client
     * @param clientDTO
     */
    void update(ClientDTO clientDTO);


    /**
     * archive clients in batch
     * @param ids
     */
    void archiveBatch(List<Long> ids);


    /**
     * restore clients in batch
     * @param ids
     */
    void restoreBatch(List<Long> ids);


    /**
     * clients page query
     * @param clientPageQueryDTO
     * @return
     */
    PageResult clientPageQuery(ClientPageQueryDTO clientPageQueryDTO);


    /**
     * get all client mapping list
     * @return
     */
    List<ClientMappingVO> getAllClient();

    /**
     * archived client page query
     * @param clientPageQueryDTO
     * @return
     */

    PageResult clientArchivedPageQuery(ClientPageQueryDTO clientPageQueryDTO);
}


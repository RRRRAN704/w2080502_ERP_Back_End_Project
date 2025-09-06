package com.erp.service.impl;

import com.erp.context.BaseContext;
import com.erp.dto.ClientDTO;
import com.erp.dto.ClientPageQueryDTO;
import com.erp.dto.DepartmentDTO;
import com.erp.dto.DepartmentPageQueryDTO;
import com.erp.entity.Client;
import com.erp.entity.Department;
import com.erp.entity.Order;
import com.erp.mapper.ClientMapper;
import com.erp.mapper.DepartmentMapper;
import com.erp.result.PageResult;
import com.erp.service.ClientService;
import com.erp.service.DepartmentService;
import com.erp.vo.ClientMappingVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientMapper clientMapper;

    /**
     * add client
     * @param clientDTO
     */
    @Override
    public void addClient(ClientDTO clientDTO) {
    Client client = new Client();
    BeanUtils.copyProperties(clientDTO,client);
    clientMapper.insert(client);
    }


    /**
     * update client
     * @param clientDTO
     */
    @Override
    public void update(ClientDTO clientDTO) {

        Client client = new Client();
        BeanUtils.copyProperties(clientDTO,client);
        clientMapper.update(client);
    }

    /**
     * archive clients in batch
     * @param ids
     */
    @Override
    public void archiveBatch(List<Long> ids) {
            for (Long id : ids) {
                Client client = new Client();
                client.setId(id);
                client.setIsArchived(1);
                clientMapper.update(client);
            }
    }

    /**
     * restore clients in batch
     * @param ids
     */
    @Override
    public void restoreBatch(List<Long> ids) {
        for (Long id : ids) {
            Client client = new Client();
            client.setId(id);
            client.setIsArchived(0);
            clientMapper.update(client);
        }
    }


    /**
     * clients page query
     * @param clientPageQueryDTO
     * @return
     */
    @Override
    public PageResult clientPageQuery(ClientPageQueryDTO clientPageQueryDTO) {
        PageHelper.startPage(clientPageQueryDTO.getPage(),clientPageQueryDTO.getPageSize());

        if(BaseContext.getCurrentRole().equals("Manager")){
            Long departmentId = BaseContext.getCurrentDepartment();
            Page<Client> client = clientMapper.managerPageQuery(clientPageQueryDTO,departmentId);
            long total = client.getTotal();
            List<Client> records = client.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Client> client = clientMapper.consultantPageQuery(clientPageQueryDTO, employeeId);
            long total = client.getTotal();
            List<Client> records = client.getResult();
            return new PageResult(total,records);
        }
    }


    /**
     * get all client list
     * @return
     */
    @Override
    public List<ClientMappingVO> getAllClient() {
     List<Client> list = clientMapper.getAllClient();
     List<ClientMappingVO> clientMappingVOList = new ArrayList<>();

     for (Client client : list) {
         ClientMappingVO clientMappingVO = new ClientMappingVO();
         BeanUtils.copyProperties(client,clientMappingVO);
         clientMappingVOList.add(clientMappingVO);
     }
     return clientMappingVOList;
    }


    /**
     * archived clients page query
     * @param clientPageQueryDTO
     * @return
     */
    @Override
    public PageResult clientArchivedPageQuery(ClientPageQueryDTO clientPageQueryDTO) {
        PageHelper.startPage(clientPageQueryDTO.getPage(),clientPageQueryDTO.getPageSize());

        if(BaseContext.getCurrentRole().equals("Manager")){
            Long departmentId = BaseContext.getCurrentDepartment();
            Page<Client> client = clientMapper.managerArchivedPageQuery(clientPageQueryDTO,departmentId);
            long total = client.getTotal();
            List<Client> records = client.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Client> client = clientMapper.consultantArchivedPageQuery(clientPageQueryDTO, employeeId);
            long total = client.getTotal();
            List<Client> records = client.getResult();
            return new PageResult(total,records);
        }
    }
}

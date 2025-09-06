package com.erp.service.impl;

import com.erp.context.BaseContext;
import com.erp.dto.ContractDTO;
import com.erp.dto.ContractPageQueryDTO;
import com.erp.entity.Client;
import com.erp.entity.Contract;
import com.erp.mapper.ContractMapper;
import com.erp.result.PageResult;
import com.erp.service.ClientService;
import com.erp.service.ContractService;
import com.erp.vo.ContractMappingVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ContractServiceImpl implements ContractService {


    @Autowired
    ContractMapper contractMapper;
    /**
     * add contracts
     * @param contractDTO
     */
    @Override
    public void addContract(ContractDTO contractDTO) {
     Contract contract = new Contract();
     BeanUtils.copyProperties(contractDTO,contract);
     contractMapper.insert(contract);
    }


    /**
     * update contracts
     * @param contractDTO
     */
    @Override
    public void update(ContractDTO contractDTO) {
        Contract contract = new Contract();
        BeanUtils.copyProperties(contractDTO,contract);
        contractMapper.update(contract);

    }


    /**
     * archive contract
     * @param ids
     */
    @Override
    public void archiveBatch(List<Long> ids) {
        for (Long id : ids) {
            Contract contract = new Contract();
            contract.setId(id);
            contract.setIsArchived(1);
            contractMapper.update(contract);
        }

    }


    /**
     * restore contrats
     * @param ids
     */
    @Override
    public void restoreBatch(List<Long> ids) {
        for (Long id : ids) {
            Contract contract = new Contract();
            contract.setId(id);
            contract.setIsArchived(0);
            contractMapper.update(contract);
        }
    }


    /**
     * contract page query
     * @param contractPageQueryDTO
     * @return
     */
    @Override
    public PageResult contractPageQuery(ContractPageQueryDTO contractPageQueryDTO) {
        PageHelper.startPage(contractPageQueryDTO.getPage(),contractPageQueryDTO.getPageSize());

        if(BaseContext.getCurrentRole().equals("Manager")){
            Long departmentId = BaseContext.getCurrentDepartment();
            Page<Contract> contracts = contractMapper.managerPageQuery(contractPageQueryDTO,departmentId);
            long total = contracts.getTotal();
            List<Contract> records = contracts.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Contract> contracts = contractMapper.consultantPageQuery(contractPageQueryDTO, employeeId);
            long total = contracts.getTotal();
            List<Contract> records = contracts.getResult();
            return new PageResult(total,records);
        }
    }


    /**
     * get contract list
     * @return
     */
    @Override
    public List<ContractMappingVO> getAllContract() {
        List<Contract> list = contractMapper.getAllContract();
        List<ContractMappingVO> contractMappingVOList = new ArrayList<>();
        for (Contract contract : list) {
            ContractMappingVO contractMappingVO = new ContractMappingVO();
            BeanUtils.copyProperties(contract,contractMappingVO);
            contractMappingVOList.add(contractMappingVO);
        }
        return contractMappingVOList;
    }


    /**
     * archived contract page query
     * @param contractPageQueryDTO
     * @return
     */
    @Override
    public PageResult contractArchivedPageQuery(ContractPageQueryDTO contractPageQueryDTO) {
        PageHelper.startPage(contractPageQueryDTO.getPage(),contractPageQueryDTO.getPageSize());
        if(BaseContext.getCurrentRole().equals("Manager")){
            Long departmentId = BaseContext.getCurrentDepartment();
            Page<Contract> contracts = contractMapper.managerArchivedPageQuery(contractPageQueryDTO,departmentId);
            long total = contracts.getTotal();
            List<Contract> records = contracts.getResult();
            return new PageResult(total,records);
        }else{
            Long employeeId = BaseContext.getCurrentId();
            Page<Contract> contracts = contractMapper.consultantArchivedPageQuery(contractPageQueryDTO, employeeId);
            long total = contracts.getTotal();
            List<Contract> records = contracts.getResult();
            return new PageResult(total,records);
        }
    }
}

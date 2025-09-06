package com.erp.controller.employee;

import com.erp.constant.MessageConstant;
import com.erp.entity.Position;
import com.erp.result.Result;
import com.erp.service.*;
import com.erp.utils.AliOssUtil;
import com.erp.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/employee/common")
@Api(tags = "Common Api")
@Slf4j
public class CommonController {



    @Autowired
    private AliOssUtil aliOssUtil;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ContractService contractService;


    /**
     * file upload
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("File upload")
    public Result<String> upload(MultipartFile file) {
        log.info("File upload:{}", file);
        try {


            //Original file name
            String originalFilename = file.getOriginalFilename();
            //get original file extention string
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            //generate object name
            String objectName = UUID.randomUUID().toString() + extension;

            //get file path
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);

        } catch (IOException e) {
            log.error("File upload error：{}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }


    /**
     * get employee name and id mapping
     * @return
     */
    @GetMapping("/employee")
    @ApiOperation("Get employee mapping details")
    public Result<List<EmployeeMappingVO>> getAllEmployee() {
        List<EmployeeMappingVO> list = employeeService.getAllEmployee();
        return Result.success(list);
    }


    /**
     * get id and position name mapping
     * @return
     */
    @GetMapping("/position")
    @ApiOperation("Get position mapping details")
    public Result<List<PositionMappingVO>> getAllPosition() {
        List<PositionMappingVO> list = positionService.getAllPosition();
        return Result.success(list);
    }


    /**
     * get id and department name mapping
     * @return
     */
    @GetMapping("/department")
    @ApiOperation("Get department mapping details")
    public Result<List<DepartmentMappingVO>> getAllDepartment() {
        List<DepartmentMappingVO> list = departmentService.getAllDepartment();
        return Result.success(list);
    }


    /**
     * get id and client name mapping
     * @return
     */
    @GetMapping("/client")
    @ApiOperation("Get client mapping details")
    public Result<List<ClientMappingVO>> getAllClient() {
        List<ClientMappingVO> list = clientService.getAllClient();
        return Result.success(list);
    }


    /**
     * get contract id and contract name and number mapping
     * @return
     */
    @GetMapping("/contract")
    @ApiOperation("Get contract mapping details")
    public Result<List<ContractMappingVO>> getAllContract() {
        List<ContractMappingVO> list = contractService.getAllContract();
        return Result.success(list);
    }




}

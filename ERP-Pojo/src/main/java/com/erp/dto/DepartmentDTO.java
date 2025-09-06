package com.erp.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * department info
 */
@Data

public class DepartmentDTO implements Serializable {


    private Long id;
    private String departmentName;
}

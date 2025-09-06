package com.erp.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Login result object that will be sent to the front end ")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("Employee Id")
    private Long id;

    @ApiModelProperty("username")
    private String username;

    @ApiModelProperty("fullname")
    private String name;

    @ApiModelProperty("jwt token")
    private String token;

    @ApiModelProperty("employee role")
    private String position;

    @ApiModelProperty("department Id")
    private Long departmentId;

}


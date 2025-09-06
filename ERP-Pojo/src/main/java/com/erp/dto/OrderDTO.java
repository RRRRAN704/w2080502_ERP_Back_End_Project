package com.erp.dto;

import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * archive/unarchive order
 */
@Data
public class OrderDTO implements Serializable {
    private Long id;
    private Integer isArchived;

}

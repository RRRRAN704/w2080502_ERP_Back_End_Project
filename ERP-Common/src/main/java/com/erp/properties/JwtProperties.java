package com.erp.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "erp.jwt")
@Data
public class JwtProperties {


    /**
     * admin-end jwt properties
     */
    private String adminSecretKey;
    private long adminTtl;
    private String adminTokenName;

//    /**
//     * client-end jwt properties
//     */
//    private String userSecretKey;
//    private long userTtl;
//    private String userTokenName;


}

package com.erp.annotation;





/*Custom annotation used to identify a method that
 requires automatic field filling processing*/


import com.erp.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AutoFill {

    //Database operation type: update or insert
    OperationType value();
}

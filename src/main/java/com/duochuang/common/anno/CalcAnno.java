/***********************************************
 * File Name: CalcAnno
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 05 06 2019 15:33
 ***********************************************/

package com.duochuang.common.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CalcAnno {
    String value() default "";
}

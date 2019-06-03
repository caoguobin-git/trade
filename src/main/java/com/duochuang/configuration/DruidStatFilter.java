/***********************************************
 * File Name: DruidStatFilter
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 24 05 2019 14:26
 ***********************************************/

package com.duochuang.configuration;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * druid过滤器.
 */
@WebFilter(filterName="druidWebStatFilter",
        urlPatterns="/*",
        initParams={
                @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")//忽略资源
        }
)
public class DruidStatFilter  extends WebStatFilter {
}

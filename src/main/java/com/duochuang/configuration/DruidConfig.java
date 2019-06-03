/***********************************************
 * File Name: DruiDConfig
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 24 05 2019 14:23
 ***********************************************/

package com.duochuang.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DruidConfig {
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        return new DruidDataSource();
    }
}

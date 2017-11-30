package com.demo;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.demo.db.DataSourceProxy;
import com.lorne.core.framework.utils.config.ConfigHelper;

/**
 * create by lorne on 2017/8/17
 */
@Configuration
public class DataSourceConfig {

    private static Logger log = LoggerFactory.getLogger(DataSourceConfig.class);

    private ConfigHelper configHelper;

    public void reloadDataSource() {
        configHelper = new ConfigHelper("db.properties");

        log.debug("重载数据源开始>>>");
        log.debug("共扫描到数据源: {}", JSON.toJSONString(configHelper.getStringArrayValue("db.name")));
        String[] names = configHelper.getStringArrayValue("db.name");
        for(String name:names){
            DruidDataSource dataSource = new DruidDataSource();
            dataSource.setUrl(configHelper.getStringValue(String.format("%s.datasource.url",name)));
            dataSource.setUsername(configHelper.getStringValue(String.format("%s.datasource.username",name)));//用户名
            dataSource.setPassword(configHelper.getStringValue(String.format("%s.datasource.password",name)));//密码
            dataSource.setInitialSize(3);
            dataSource.setMaxActive(20);
            dataSource.setMinIdle(0);
            dataSource.setMaxWait(60000);
            dataSource.setValidationQuery("SELECT 1");
            dataSource.setTestOnBorrow(false);
            dataSource.setTestWhileIdle(true);
            dataSource.setPoolPreparedStatements(false);
            DataSourceProxy.addDataSource(name,dataSource);
            log.debug("添加库 {} 到数据源", name);
        }
        log.debug("重载数据源结束<<<");
    }

    @Bean
    public DataSource dataSource() {
        DataSourceProxy dataSourceProxy = new DataSourceProxy();
        reloadDataSource();
        return dataSourceProxy;
    }
}
package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.DataSourceConfig;
import com.demo.service.DbService;

/**
 * create by lorne on 2017/11/8
 */
@RestController
@RequestMapping("/db/{dbName}/")
public class DBController {

    private static final Logger log = LoggerFactory.getLogger(DBController.class);

    @Autowired
    private DbService dbService;

    @Autowired
    private DataSourceConfig dataSourceConfig;


    @RequestMapping(value = "save",method = RequestMethod.GET)
    public int save(@RequestParam("name") String name){
        return dbService.save(name);
    }

    @RequestMapping(value = "reload", method = RequestMethod.GET)
    public String reload() {
        log.debug("重新加载数据源...");
        dataSourceConfig.reloadDataSource();
        return "数据源刷新完毕";
    }
}

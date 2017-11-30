package com.demo.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.dao.TestDao;

/**
 * create by lorne on 2017/11/8
 */
@Repository
public class TestDaoImpl implements TestDao {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int save(String name) {
        String sql = "insert into t_test(name) values(?)";
        // DataAccessException -- spring 5 中的异常类型
        return jdbcTemplate.update(sql, name);
    }
}

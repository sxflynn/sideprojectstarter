package com.sideprojectstarter.dao;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestingDatabaseConfig.class)
public abstract class BaseDaoTests {

    @Autowired
    protected DataSource dataSource;

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }

}

package com.sideprojectstarter.dao;
import com.sideproject.starter.dao.JdbcHelloWorldDao;
import org.junit.Before;
import org.junit.Test;

public class JdbcHelloWorldDaoTests extends BaseDaoTests{

    private JdbcHelloWorldDao dao;

    @Before
    public void setup(){
        dao = new JdbcHelloWorldDao(dataSource);
    }

    @Test
    public void get_balance_returns_correct_balance(){

    }









}

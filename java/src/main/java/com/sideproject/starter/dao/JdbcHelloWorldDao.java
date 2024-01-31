package com.sideproject.starter.dao;

import com.sideproject.starter.exception.DaoException;
import com.sideproject.starter.model.HelloWorld;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.lang.NonNull;


import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcHelloWorldDao implements HelloWorldDao {

    private JdbcTemplate jdbcTemplate;
    public JdbcHelloWorldDao(@NonNull DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public HelloWorld getHelloWorld() {
        HelloWorld helloWorld = new HelloWorld();
        String sql = "SELECT message FROM greetings LIMIT 1";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            if (results.next()){
                helloWorld.setMessage(results.getString("message"));
            } else {
                throw new DaoException("No message found");
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database");
        }
        return helloWorld;
    }


    @Override
    public HelloWorld addMessage(HelloWorld helloWorld){
        String sql = "INSERT INTO greetings (message) VALUES (?);";
        try {
            int results = jdbcTemplate.update(sql, helloWorld.getMessage());
        }catch (NullPointerException e){
            throw new DaoException("Returns null.", e);
        }catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database.", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation.", e);

        }
        return helloWorld;
    }

    @Override
    public List<HelloWorld> getAllHelloWorlds() {
        List<HelloWorld> helloWorldList = new ArrayList<>();
        String sql = "SELECT message FROM greetings";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()){
                helloWorldList.add(mapRowsToHello(results));
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to the database");
        }
        return helloWorldList;
    }
    public HelloWorld mapRowsToHello(SqlRowSet rowSet){
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMessage(rowSet.getString("message"));
        return helloWorld;
    }

}

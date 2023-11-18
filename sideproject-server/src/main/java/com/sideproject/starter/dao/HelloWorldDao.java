package com.sideproject.starter.dao;

import com.sideproject.starter.model.HelloWorld;

import java.util.List;

public interface HelloWorldDao {

    public HelloWorld getHelloWorld();

    public List<HelloWorld> getAllHelloWorlds();

    public HelloWorld addMessage(HelloWorld helloWorld);
}

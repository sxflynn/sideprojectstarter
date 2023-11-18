package com.sideproject.starter.controller;

import com.sideproject.starter.dao.HelloWorldDao;
import com.sideproject.starter.exception.DaoException;
import com.sideproject.starter.model.HelloWorld;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloWorldController {

    private HelloWorldDao helloWorldDao;

    public HelloWorldController(HelloWorldDao helloWorldDao) {
        this.helloWorldDao = helloWorldDao;
    }


    @RequestMapping(path = "/",method = RequestMethod.GET)
    public HelloWorld getMessage(){
        try {
            HelloWorld helloWorld = new HelloWorld();
            helloWorld.setMessage(helloWorldDao.getHelloWorld().getMessage());
            return helloWorld;
        } catch (ResourceAccessException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public List<HelloWorld> getAllMessages(){
        List<HelloWorld> helloWorldList = new ArrayList<>();
        helloWorldList = helloWorldDao.getAllHelloWorlds();
        if (helloWorldList == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "HelloWorld List Not Found");
        } else {
            return helloWorldList;
        }
    }

    @RequestMapping(path = "/new", method = RequestMethod.POST)
    public HelloWorld setMessage(@Valid @RequestBody HelloWorld helloMessage ){
        try{
            return helloWorldDao.addMessage(helloMessage);
        } catch (DaoException e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Error adding new message", e);
        }
    }


}

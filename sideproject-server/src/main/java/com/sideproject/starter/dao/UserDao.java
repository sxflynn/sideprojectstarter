package com.sideproject.starter.dao;

import com.sideproject.starter.model.RegisterUserDto;
import com.sideproject.starter.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getUserById(int id);

    User getUserByUsername(String username);

    User createUser(RegisterUserDto user);
    User getUserFromPrincipal(Principal principal);
}

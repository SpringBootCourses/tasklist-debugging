package com.example.tasklist.service;

import com.example.tasklist.domain.user.User;

public interface UserService {

    User getById(Long id);

    User getByUsername(String username);

    User create(User user);

}

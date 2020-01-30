package com.tehkesih.springboot.userservice.impl;

import com.tehkesih.springboot.shared.Utils;
import com.tehkesih.springboot.ui.model.request.UserDetailsRequestModel;
import com.tehkesih.springboot.ui.model.response.UserRest;
import com.tehkesih.springboot.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    Map<String,UserRest> users;

//    @Autowired
    Utils utils;

    public UserServiceImpl(){}

    @Autowired
    public UserServiceImpl(Utils utils){

        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetails) {

        UserRest returnValue = new UserRest();
        returnValue.setEmail(userDetails.getEmail());
        returnValue.setFirstName(userDetails.getFirstName());
        returnValue.setLastName(userDetails.getLastName());

        String userId = utils.generateUserId();
        returnValue.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, returnValue);

        return returnValue;
    }
}

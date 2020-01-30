package com.tehkesih.springboot.userservice;

import com.tehkesih.springboot.ui.model.request.UserDetailsRequestModel;
import com.tehkesih.springboot.ui.model.response.UserRest;

public interface UserService {

    UserRest createUser(UserDetailsRequestModel userDetails);
}

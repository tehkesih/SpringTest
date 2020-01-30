package com.tehkesih.springboot.ui.controller;

import com.tehkesih.springboot.exception.UserServiceException;
import com.tehkesih.springboot.ui.model.request.UpdateUserDetailsRequestModel;
import com.tehkesih.springboot.ui.model.request.UserDetailsRequestModel;
import com.tehkesih.springboot.ui.model.response.UserRest;

import com.tehkesih.springboot.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(@RequestParam(value="page" , defaultValue = "1") int page,
                           @RequestParam(value="limit", defaultValue = "50") int limit){
        return "get user was called page number " + page + " Limit : " + limit;
    }

    @GetMapping(path="/{userId}",
            produces = { MediaType.APPLICATION_XML_VALUE ,
                         MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> getUser(@PathVariable ("userId") String id){
//        UserRest returnValue = new UserRest();

//        returnValue.setEmail("test");
//        returnValue.setFirstName("Tehkesih");
//        returnValue.setLastName("Windy");
//        returnValue.setUserId(id);

        if (true) throw new UserServiceException("User exception is thrown");

        if (users.containsKey(id)){
            return new ResponseEntity<>(users.get(id), HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

//        return new ResponseEntity<>(returnValue, HttpStatus.BAD_GATEWAY);
    }

    @PostMapping (
            produces = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE }
                    ,
            consumes = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {

        UserRest returnValue = userService.createUser(userDetails);

        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @PutMapping (path = "/{userId}",
            produces = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE }
            ,
            consumes = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserRest> updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetailsRequestModel updateUserDetails) {

        UserRest userDetails = users.get(userId);

        userDetails.setFirstName(updateUserDetails.getFirstName());
        userDetails.setLastName(updateUserDetails.getLastName());

        users.put(userId, userDetails);

        return new ResponseEntity<>(userDetails, HttpStatus.OK);
    }

    @DeleteMapping (path = "/{userId}" ,
            produces = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE }
            ,
            consumes = { MediaType.APPLICATION_XML_VALUE ,
                    MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity deleteUser(@PathVariable String userId) {

        if (users.containsKey(userId))
            users.remove(userId);


        return ResponseEntity.ok(HttpStatus.OK);
    }
}

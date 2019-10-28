/*
 * Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package com.jalasoft.hr.controller;

import com.jalasoft.hr.entities.User;
import com.jalasoft.hr.error_handler.DatabaseException;
import com.jalasoft.hr.model.DBManager;
import com.jalasoft.hr.responses.ErrorResponse;
import com.jalasoft.hr.responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jalasoft.hr.utils.Constants.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class UserController {
    @PostMapping(value = "/user")
    public Response createUser(@RequestBody User user) {
        try {
            //user.validate();
            if (!DBManager.exist(user.getUserName(), user.getPassword())) {
                DBManager.insertUser(user);
            }
            return new Response(HttpStatus.OK.name(),
                    HttpStatus.OK.value(),
                    String.format("User %s successfully created", user.getUserName()));
        } catch (DatabaseException e) {
            return new ErrorResponse(HttpStatus.BAD_REQUEST.name(),
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage());
//        } catch (ParamsInvalidException e) {
//            return new ErrorResponse(HttpStatus.BAD_REQUEST.name(),
//                    HttpStatus.BAD_REQUEST.value(),
//                    e.getMessage());
        }
    }
}

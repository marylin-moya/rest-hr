/*
 *
 *  Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with Jalasoft.
 *
 */
package com.jalasoft.hr.controller;

import com.jalasoft.hr.entities.Cache;
import com.jalasoft.hr.entities.User;
import com.jalasoft.hr.error_handler.DatabaseException;
import com.jalasoft.hr.model.DBManager;
import com.jalasoft.hr.responses.ErrorResponse;
import com.jalasoft.hr.responses.LoginResponse;
import com.jalasoft.hr.responses.Response;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jalasoft.hr.utils.Constants.BASE_PATH;
import static com.jalasoft.hr.utils.Constants.LOGIN_PATH;

/**
 * Login Controller class to implement Rest EndPoint to generate a token
 */
@RestController
@RequestMapping(BASE_PATH)
public class LoginController {
    private static final String ROLE = "role";
    private static final String EMAIL = "email";

    @PostMapping(value = LOGIN_PATH)
    public Response loginController(@RequestBody User user) {
        try {
            //user.validateLogin();
            user = DBManager.getUser(user.getUserName(), user.getPassword());
            String key = "dev-fun2";
            String token = Jwts.builder()
                    .signWith(SignatureAlgorithm.HS256, key.getBytes())
                    .setSubject(user.getUserName())
                    .claim(ROLE, user.getRole())
                    .claim(EMAIL, user.getEmail())
                    .compact();

            //Add token to cache.
            Cache.getInstance().add(token);
            return new LoginResponse(HttpStatus.ACCEPTED.name(),
                    HttpStatus.ACCEPTED.value(),
                    String.format("Token for %s user successfully generated", user.getUserName()),
                    token);
        } catch (DatabaseException e) {
            return new ErrorResponse(HttpStatus.UNAUTHORIZED.name(),
                    HttpStatus.UNAUTHORIZED.value(),
                    e.getMessage());
//        } catch (ParamsInvalidException e) {
//            return new ErrorResponse(HttpStatus.BAD_REQUEST.name(),
//                    HttpStatus.BAD_REQUEST.value(),
//                    e.getMessage());
//        } catch (DatabaseException e) {
//            e.printStackTrace();
        }
    }
}

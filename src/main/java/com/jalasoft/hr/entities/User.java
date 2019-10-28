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

package com.jalasoft.hr.entities;

import com.jalasoft.hr.error_handler.ParamsInvalidException;
import com.jalasoft.hr.model.validate.Context;
import com.jalasoft.hr.model.validate.IValidateStrategy;
import com.jalasoft.hr.model.validate.NullOrEmptyValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * User class.
 */
public class User {
    private String userName;
    private String password;
    private String role;
    private String email;

    /**
     * Get User Name.
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set User Name.
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Get Password.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set Password.
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get Role.
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    /**
     * Set Role.
     *
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * Get email.
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set email.
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Validate user fields
     *
     * @throws ParamsInvalidException
     */
    public void validate() throws ParamsInvalidException {
        List<IValidateStrategy> vals = new ArrayList<>();
        vals.add(new NullOrEmptyValidation(this.email));
        vals.add(new NullOrEmptyValidation(this.password));
        vals.add(new NullOrEmptyValidation(this.role));
        vals.add(new NullOrEmptyValidation(this.userName));
        Context context = new Context(vals);
        context.validate();
    }
}

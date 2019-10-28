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
 * Employee class.
 */
public class Employee {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String career;
    protected String place;

    /**
     * Get id.
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Set id.
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get firstName.
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set firstName.
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get Last Name.
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set lastName.
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Get Address.
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set Address.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Get Career.
     *
     * @return
     */
    public String getCareer() {
        return career;
    }

    /**
     * Set Career.
     *
     * @param career
     */
    public void setCareer(String career) {
        this.career = career;
    }

    /**
     * Get Place.
     *
     * @return
     */
    public String getPlace() {
        return place;
    }

    /**
     * Set Place.
     *
     * @param place
     */
    public void setPlace(String place) {
        this.place = place;
    }

    /**
     * Validate Employee.
     *
     * @throws ParamsInvalidException
     */
    public void validate() throws ParamsInvalidException {
        List<IValidateStrategy> vals = new ArrayList<>();
        vals.add(new NullOrEmptyValidation(this.firstName));
        vals.add(new NullOrEmptyValidation(this.lastName));
        vals.add(new NullOrEmptyValidation(this.address));
        vals.add(new NullOrEmptyValidation(this.career));
        vals.add(new NullOrEmptyValidation(this.place));
        Context context = new Context(vals);
        context.validate();
    }
}

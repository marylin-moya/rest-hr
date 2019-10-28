/*
 * Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */
package com.jalasoft.hr.model.validate;

import com.jalasoft.hr.error_handler.ParamsInvalidException;

/**
 * NullOrEmptyValidation class.
 */
public class NullOrEmptyValidation implements IValidateStrategy {
    private String value;

    /**
     * NullOrEmptyValidation constructor.
     *
     * @param value
     */
    public NullOrEmptyValidation(String value) {
        this.value = value;
    }

    /**
     * Validate if value is empty or null.
     *
     * @return
     */
    @Override
    public void validate() throws ParamsInvalidException {
        if (null == this.value) {
            throw new ParamsInvalidException(10, "field");
        } else if (this.value.isEmpty()) {
            throw new ParamsInvalidException(11, "field");
        }
    }
}

//Hibernate

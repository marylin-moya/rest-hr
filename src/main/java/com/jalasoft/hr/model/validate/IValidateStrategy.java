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
 * IValidateStrategy interface.
 */
public interface IValidateStrategy {
    /**
     * Validate method.
     *
     * @return
     */
    void validate() throws ParamsInvalidException;
}

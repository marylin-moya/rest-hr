/*
 * Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package com.jalasoft.hr.error_handler;

/**
 * Database Exception class.
 */
public class DatabaseException extends Exception {
    String message;

    /**
     * DatabaseException constructor
     *
     * @param message
     */
    public DatabaseException(String message) {
        this.message = message;
    }

    /**
     * Get Message.
     *
     * @return
     */
    @Override
    public String getMessage() {
        return message;
    }
}

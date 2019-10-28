/*
 * Copyright (c) 2019 Jalasoft.
 *
 *  This software is the confidential and proprietary information of Jalasoft.
 *  ("Confidential Information"). You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with Jalasoft.
 */

package com.jalasoft.hr.model;

import com.jalasoft.hr.database.EmployeeQuery;
import com.jalasoft.hr.database.UserQuery;
import com.jalasoft.hr.entities.Employee;
import com.jalasoft.hr.entities.User;
import com.jalasoft.hr.error_handler.DatabaseException;

import java.sql.SQLException;
import java.util.List;

/***
 * Manage the queries to database
 */
public class DBManager {

    /**
     * Insert user.
     *
     * @param user
     * @return
     */
    public static boolean insertUser(User user) throws DatabaseException {
        boolean userCreated = new UserQuery().insert(user);
        User createdUser = new UserQuery().getUser(user.getUserName(), user.getPassword());
        if (createdUser.getUserName().isEmpty() || createdUser.getUserName() == null) {
            throw new DatabaseException(String.format("Error to create %s user", user.getUserName()));
        }
        return true;
    }

    /**
     * Get a user
     *
     * @param userName
     * @param password
     * @return
     */
    public static User getUser(String userName, String password) throws DatabaseException {
        User user = new UserQuery().getUser(userName, password);
        try {
            if (user.getUserName().isEmpty() || user.getUserName() == null) {
                throw new DatabaseException(String.format("The %s user is not authorized", userName));
            }
        } catch (NullPointerException e) {
            throw new DatabaseException(String.format("The %s user is not authorized", userName));
        }
        return user;
    }

    /**
     * Method to verify if user is in the database
     *
     * @param userName
     * @param password
     * @return
     * @throws DatabaseException
     */
    public static boolean exist(String userName, String password) throws DatabaseException {
        try {
            return new UserQuery().exists(userName, password);
        } catch (NullPointerException e) {
            return false;
        }
    }

    /**
     * Method to get all employees.
     *
     * @return
     * @throws DatabaseException
     */
    public static List<Employee> getEmployees() throws DatabaseException {
        try {
            return new EmployeeQuery().getEmployees();
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Method to create a employee
     *
     * @param employee
     * @return
     * @throws DatabaseException
     */
    public static Employee postEmployee(Employee employee) throws DatabaseException {
        try {
            return new EmployeeQuery().insertEmployee(employee);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Method to get an employee by id
     *
     * @param id
     * @return
     * @throws DatabaseException
     */
    public static Employee getEmployee(int id) throws DatabaseException {
        try {
            Employee employee = new EmployeeQuery().getEmployeeById(id);
            return employee;
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Method to delete an employee by id
     *
     * @param id
     * @throws DatabaseException
     */
    public static void deleteEmployee(int id) throws DatabaseException {
        try {
            new EmployeeQuery().deleteEmployeeById(id);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    /**
     * Method to update an employee
     *
     * @param employee
     * @return
     * @throws DatabaseException
     */
    public static Employee updateEmployee(Employee employee) throws DatabaseException {
        try {
            return new EmployeeQuery().updateEmployee(employee);
        } catch (SQLException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

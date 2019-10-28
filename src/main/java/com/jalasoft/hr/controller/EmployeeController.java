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

import com.jalasoft.hr.entities.Employee;
import com.jalasoft.hr.error_handler.DatabaseException;
import com.jalasoft.hr.error_handler.ParamsInvalidException;
import com.jalasoft.hr.model.DBManager;
import com.jalasoft.hr.responses.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.jalasoft.hr.utils.Constants.BASE_PATH;
import static com.jalasoft.hr.utils.Constants.EMPLOYEE;
import static com.jalasoft.hr.utils.Constants.EMPLOYEES;

/**
 * EmployeeController class.
 */
@RestController
@RequestMapping(BASE_PATH)
public class EmployeeController {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Get /employees endpoint method to get all employees.
     *
     * @return a json array with all the employees.
     */
    @GetMapping(value = EMPLOYEES)
    public ResponseEntity getEmployees() {
        LOGGER.info("Get all employees.");
        List<Employee> employees = null;
        try {
            employees = DBManager.getEmployees();
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.name(),
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    /**
     * Post /employees endpoint method to create an employee.
     *
     * @param employee Employee instance to create the employee.
     * @return a json object with the employee information.
     */
    @PostMapping(value = EMPLOYEES)
    public ResponseEntity postEmployees(@RequestBody Employee employee) {
        LOGGER.info("Create {} {} employee", employee.getFirstName(), employee.getLastName());
        Employee employee1 = null;
        try {
            employee.validate();
            employee1 = DBManager.postEmployee(employee);
        } catch (DatabaseException | ParamsInvalidException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.name(),
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee1, HttpStatus.CREATED);
    }

    /**
     * Get /employees/{id} endpoint method to get an employee by id.
     *
     * @param id employee.
     * @return the employee json object if it is found.
     */
    @GetMapping(value = EMPLOYEE)
    public ResponseEntity getEmployeeById(@PathVariable int id) {
        LOGGER.info("Get an employee given {} id", id);
        Employee employee = null;
        try {
            employee = DBManager.getEmployee(id);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.name(),
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(employee, HttpStatus.FOUND);
    }

    /**
     * Patch /employees/{id} endpoint method to update an employee by id.
     *
     * @param id       employee.
     * @param employee instance to update.
     * @return the employee json object updated.
     */
    @PatchMapping(value = EMPLOYEE)
    public ResponseEntity patchEmployeeById(@PathVariable int id, @RequestBody Employee employee) {
        LOGGER.info("Update an employee given {} id", id);
        Employee employee1 = null;
        try {
            employee.setId(id);
            employee.validate();
            employee1 = DBManager.updateEmployee(employee);
        } catch (DatabaseException | ParamsInvalidException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.name(),
                    HttpStatus.BAD_REQUEST.value(),
                    e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employee1, HttpStatus.OK);
    }

    /**
     * Delete /employees/{id} endpoint method to delete an employee by id.
     *
     * @param id employee.
     * @return none.
     */
    @DeleteMapping(value = EMPLOYEE)
    public ResponseEntity deleteEmployeeById(@PathVariable int id) {
        LOGGER.info("Delete an employee given {} id", id);
        try {
            DBManager.deleteEmployee(id);
        } catch (DatabaseException e) {
            ErrorResponse errorResponse = new ErrorResponse(
                    HttpStatus.NOT_FOUND.name(),
                    HttpStatus.NOT_FOUND.value(),
                    e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

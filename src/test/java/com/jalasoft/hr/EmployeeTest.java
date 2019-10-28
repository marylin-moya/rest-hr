package com.jalasoft.hr;

import com.jalasoft.hr.entities.Employee;
import com.jalasoft.hr.error_handler.ParamsInvalidException;
import org.junit.Test;

public class EmployeeTest {
    @Test(expected = ParamsInvalidException.class)
    public void ExceptionIsRaisedWhenEmptyFields() throws ParamsInvalidException {
        Employee employee = new Employee();
        employee.setFirstName("");
        employee.setLastName("");
        employee.setAddress("");
        employee.setPlace("");
        employee.setCareer("");
        employee.validate();
    }

    @Test(expected = ParamsInvalidException.class)
    public void ExceptionIsRaisedWhenNullFields() throws ParamsInvalidException {
        Employee employee = new Employee();
        employee.setFirstName(null);
        employee.setLastName(null);
        employee.setAddress(null);
        employee.setPlace(null);
        employee.setCareer(null);
        employee.validate();
    }
}

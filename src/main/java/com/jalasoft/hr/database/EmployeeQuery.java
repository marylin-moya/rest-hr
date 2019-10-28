package com.jalasoft.hr.database;

import com.jalasoft.hr.entities.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeQuery {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String ID = "id";
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String ADDRESS = "address";
    private static final String CAREER = "career";
    private static final String PLACE = "place";
    Connection conn = null;

    public EmployeeQuery() {
        conn = ConnectionDB.getInstance().getConn();
    }

    public List<Employee> getEmployees() throws SQLException {
        LOGGER.info("Get all employees");
        List<Employee> employees = new ArrayList<>();
        String sql = "select id, firstName, lastName, address, career, place from employee";
        PreparedStatement statement = conn.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt(ID));
            employee.setFirstName(resultSet.getString(FIRST_NAME));
            employee.setLastName(resultSet.getString(LAST_NAME));
            employee.setAddress(resultSet.getString(ADDRESS));
            employee.setCareer(resultSet.getString(CAREER));
            employee.setPlace(resultSet.getString(PLACE));
            employees.add(employee);
        }
        return employees;
    }

    public Employee insertEmployee(Employee employee) throws SQLException {
        LOGGER.info("Insert '{}' '{}' employee.", employee.getFirstName(), employee.getLastName());
        String sql = "Insert into employee (firstName, lastName, address, career, place) values (?,?,?,?,?)";
        PreparedStatement statement = null;
        statement = conn.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getAddress());
        statement.setString(4, employee.getCareer());
        statement.setString(5, employee.getPlace());
        statement.execute();
        return this.getEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) throws SQLException {
        LOGGER.info("Insert '{}' '{}' employee.", employee.getFirstName(), employee.getLastName());
        String sql = "update employee set firstName = ? , lastName = ?, address = ?, career = ?, place = ? where id = ?";
        getEmployeeById(employee.getId());
        PreparedStatement statement = null;
        statement = conn.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        statement.setString(3, employee.getAddress());
        statement.setString(4, employee.getCareer());
        statement.setString(5, employee.getPlace());
        statement.setInt(6, employee.getId());
        statement.execute();
        return this.getEmployee(employee);
    }

    public Employee getEmployee(Employee employee) throws SQLException {
        LOGGER.info("Get {} {} user", employee.getFirstName(), employee.getLastName());
        String sql = "select id, firstName, lastName, address, career, place from employee where firstName = ? and lastName = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, employee.getFirstName());
        statement.setString(2, employee.getLastName());
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            employee.setId(resultSet.getInt(ID));
        }
        return employee;
    }

    public Employee getEmployeeById(int id) throws SQLException {
        LOGGER.info("Get employee by {} id", id);
        String sql = "select id, firstName, lastName, address, career, place from employee where id = ?";
        Employee employee = new Employee();
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        boolean result = false;
        while (resultSet.next()) {
            result = true;
            employee.setId(resultSet.getInt(ID));
            employee.setFirstName(resultSet.getString(FIRST_NAME));
            employee.setLastName(resultSet.getString(LAST_NAME));
            employee.setAddress(resultSet.getString(ADDRESS));
            employee.setCareer(resultSet.getString(CAREER));
            employee.setPlace(resultSet.getString(PLACE));
        }
        if (!result) {
            throw new SQLException("Employee not found");
        }
        return employee;
    }

    public void deleteEmployeeById(int id) throws SQLException {
        LOGGER.info("Delete employee by {} id", id);
        getEmployeeById(id);
        String sql = "delete from employee where id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, id);
        statement.execute();
    }
}

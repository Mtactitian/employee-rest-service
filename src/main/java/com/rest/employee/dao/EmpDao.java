package com.rest.employee.dao;

import com.rest.employee.exception.DaoException;
import com.rest.employee.model.Employee;
import com.rest.employee.model.dto.EmployeeDto;

import java.util.List;

public interface EmpDao {
    Employee getByName(String name) throws DaoException;
    EmployeeDto saveEmployee(EmployeeDto employeeDto) throws DaoException;
    void deleteEmployee(Integer id) throws DaoException;
    List getAllNamesAndIds();
}

package com.rest.employee.service;

import com.rest.employee.dao.EmpDao;
import com.rest.employee.exception.DaoException;
import com.rest.employee.exception.ServiceException;
import com.rest.employee.model.Employee;
import com.rest.employee.model.dto.EmployeeDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpService {

    private EmpDao empDao;

    public EmpService(EmpDao empDao) {
        this.empDao = empDao;
    }

    public Employee getEmployeeByName(String name){
        try {
            return empDao.getByName(name);
        } catch (DaoException ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        try {
            return empDao.saveEmployee(employeeDto);
        } catch (DaoException ex) {
            throw new ServiceException(HttpStatus.CONFLICT, ex.getMessage());
        }
    }

    public void deleteEmployee(Integer id){
        try {
            empDao.deleteEmployee(id);
        } catch (DaoException ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    public List getAllEmpNamesIds(){
        return empDao.getAllNamesAndIds();
    }
}

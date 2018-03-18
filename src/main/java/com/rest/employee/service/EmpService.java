package com.rest.employee.service;

import com.rest.employee.dao.EmpDao;
import com.rest.employee.exception.DaoException;
import com.rest.employee.exception.ServiceException;
import com.rest.employee.model.Employee;
import com.rest.employee.model.dto.EmployeeDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        try {
            return empDao.saveEmployee(employeeDto);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteEmployee(Integer id){
        try {
            empDao.deleteEmployee(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}

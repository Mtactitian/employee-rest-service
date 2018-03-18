package com.rest.employee.dao.impl;

import com.rest.employee.dao.EmpDao;
import com.rest.employee.exception.DaoException;
import com.rest.employee.model.Department;
import com.rest.employee.model.Employee;
import com.rest.employee.model.dto.EmployeeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

@Repository
public class EmpDaoImpl implements EmpDao {

    private Logger logger = LoggerFactory.getLogger(EmpDaoImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Employee getByName(String name) throws DaoException {
        try {
            return entityManager.createQuery("select e from Employee e where e.name = :name"
                    , Employee.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException noResult) {
            throw new DaoException(HttpStatus.NOT_FOUND, "Employee with given id does not exists", noResult.getCause());
        }
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) throws DaoException {
        try {

            Department department = entityManager.createQuery("select d from Department d join fetch d.employees " +
                            "where d.departmentNumber = :id"
                    , Department.class)
                    .setParameter("id", employeeDto.getDeptId())
                    .getSingleResult();
            Employee employeeToBeSaved = Employee.builder()
                    .commission(employeeDto.getCommission())
                    .department(department)
                    .hireDate(employeeDto.getHireDate())
                    .id(employeeDto.getId())
                    .job(employeeDto.getJob())
                    .salary(employeeDto.getSalary())
                    .managerId(employeeDto.getManagerId())
                    .name(employeeDto.getName())
                    .build();
            department.getEmployees().add(employeeToBeSaved);
            entityManager.persist(employeeToBeSaved);
            return employeeDto;
        } catch (EntityExistsException | NoResultException ex) {
            throw new DaoException(HttpStatus.CONFLICT, "Employee with given id already exists or department's id is wrong",
                    ex.getCause());
        }
    }

    @Override
    public void deleteEmployee(Integer id) throws DaoException {
        try {
            Employee employee = entityManager.getReference(Employee.class, id);
            entityManager.remove(employee);
        } catch (EntityNotFoundException ex) {
            throw new DaoException(HttpStatus.NOT_FOUND, "Employee with given id does not exists", ex.getCause());
        }
    }
}

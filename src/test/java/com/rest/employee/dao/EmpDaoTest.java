package com.rest.employee.dao;

import com.rest.employee.exception.DaoException;
import com.rest.employee.model.Employee;
import com.rest.employee.model.dto.EmployeeDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.rest.employee.model.EmployeeAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager
public class EmpDaoTest {

    @Autowired
    private EmpDao empDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void shouldSaveEmployee() throws DaoException {

        empDao.saveEmployee(createEmployeeDto());
        entityManager.flush();
        entityManager.clear();

        Employee actualEmployee = empDao.getByName("ALEX");

        assertThat(actualEmployee)
                .hasName("ALEX")
                .hasId(10)
                .hasJob("DEV")
                .hasCommission(0);
    }

    @Test
    public void shouldDeleteEmployee() throws DaoException {
        assertThatCode(() -> {
            empDao.saveEmployee(createEmployeeDto());
            entityManager.flush();
            entityManager.clear();

            empDao.deleteEmployee(10);
        }).doesNotThrowAnyException();
    }

    private EmployeeDto createEmployeeDto() {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setDeptId(10);
        employeeDto.setName("ALEX");
        employeeDto.setJob("DEV");
        employeeDto.setId(10);
        employeeDto.setCommission(0);
        return employeeDto;
    }
}

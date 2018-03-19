package com.rest.employee.controller;

import com.rest.employee.exception.RestExceptionHandler;
import com.rest.employee.exception.ServiceException;
import com.rest.employee.model.Employee;
import com.rest.employee.service.EmpService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class EmpControllerTest {

    private static final int EMPLOYEE_ID = 10;
    private static final String EMPLOYEE_NAME = "Geralt of Rivia";

    private MockMvc mockMvc;
    private EmpService empService;

    @Before
    public void setUp() {
        empService = mock(EmpService.class);

        mockMvc = MockMvcBuilders.standaloneSetup(new EmpController(empService))
                .setControllerAdvice(new RestExceptionHandler())
                .build();
        initMocks(this);
    }

    @Test
    public void getByNameSuccessTest() throws Exception {
        Employee expectedEmployee = createEmployee();

        when(empService.getEmployeeByName(EMPLOYEE_NAME)).thenReturn(expectedEmployee);

        mockMvc.perform(get("/employee").param("name", EMPLOYEE_NAME))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$.id").value(EMPLOYEE_ID))
                .andExpect(jsonPath("$.name").value(EMPLOYEE_NAME));

        verify(empService).getEmployeeByName(EMPLOYEE_NAME);
    }

    @Test
    public void getByNameNotFoundTest() throws Exception {
        final String errorMessage = "Not Exists";

        when(empService.getEmployeeByName(EMPLOYEE_NAME))
                .thenThrow(new ServiceException(HttpStatus.NOT_FOUND, errorMessage));

        mockMvc.perform(get("/employee").param("name", EMPLOYEE_NAME))
                .andExpect(status().isNotFound())
                .andExpect(content().string(errorMessage));

        verify(empService).getEmployeeByName(EMPLOYEE_NAME);
    }

    private Employee createEmployee() {
        return Employee.builder()
                .id(EMPLOYEE_ID)
                .name(EMPLOYEE_NAME).build();
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(empService);
    }

}
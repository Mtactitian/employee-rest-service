package com.rest.employee;

import com.rest.employee.controller.EmpController;
import com.rest.employee.model.dto.EmployeeDto;
import com.rest.employee.service.EmpService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@DirtiesContext
public class BaseEmpControllerTestClass {

    @MockBean
    private EmpService empService;

    @Before
    public void setup() {

        when(empService.getAllEmpNamesIds())
                .thenReturn(Arrays.asList(
                        EmployeeDto.builder().id(7839).name("KING").deptId(10).build(),
                        EmployeeDto.builder().id(7698).name("BLAKE").deptId(30).build(),
                        EmployeeDto.builder().id(7782).name("CLARK").deptId(10).build()
                ));

        RestAssuredMockMvc.standaloneSetup(new EmpController(empService));
    }
}
package com.rest.employee.model;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

public class EmployeeTest {

    @Test
    public void hashcodeEqualsTest(){

        EqualsVerifier.forClass(Employee.class)
                .withOnlyTheseFields("id")
                .verify();
    }
}

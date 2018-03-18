package com.rest.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.sql.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@NoArgsConstructor //needed For JackSon
@ToString
public class EmployeeDto {

    @NotNull
    @Digits(integer = 4, fraction = 0)
    private Integer id;

    @Length(min = 2, max = 10)
    private String name;

    @Length(max = 10)
    private String job;

    @Digits(integer = 4, fraction = 0)
    private Integer managerId;

    @Past
    private Date hireDate;

    @Digits(integer = 7, fraction = 2)
    private Double salary;

    @Digits(integer = 7, fraction = 2)
    private Integer commission;

    @NotNull
    @Digits(integer = 2, fraction = 0)
    private Integer deptId;
}

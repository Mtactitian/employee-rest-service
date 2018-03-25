package com.rest.employee.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@NoArgsConstructor //needed For JackSon
@ToString
@Setter
@Builder
public class EmployeeDto implements Serializable {

    private static final long serialVersionUID = -5417448339203137166L;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate hireDate;

    @Digits(integer = 7, fraction = 2)
    private Double salary;

    @Digits(integer = 7, fraction = 2)
    private Integer commission;

    @NotNull
    @Digits(integer = 2, fraction = 0)
    private Integer deptId;
}

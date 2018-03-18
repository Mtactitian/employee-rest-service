package com.rest.employee.model;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "DEPT")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode(of = "departmentNumber")
@AllArgsConstructor
@Immutable
public class Department {

    @Id
    @Column(name = "deptno")
    private Integer departmentNumber;

    @Column(name = "dname")
    private String departmentName;

    @Column(name = "loc")
    private String location;

    @OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST)
    private Set<Employee> employees;
}

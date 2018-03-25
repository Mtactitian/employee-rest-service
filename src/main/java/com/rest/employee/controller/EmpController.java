package com.rest.employee.controller;

import com.rest.employee.model.dto.EmployeeDto;
import com.rest.employee.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Collections.singletonMap;

@RestController
public class EmpController {
    private Logger logger = LoggerFactory.getLogger(EmpController.class);

    private EmpService empService;

    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping(path = "/employee",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getByName(@RequestParam String name) {
        return ResponseEntity.ok(empService.getEmployeeByName(name));
    }

    @PostMapping(path = "/employee",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveEmployee(@RequestBody @Valid EmployeeDto employeeDto, BindingResult result) {

        if (result.hasErrors()) {
            logger.warn("Employee Dto has wrong fields");
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(empService.saveEmployee(employeeDto));
    }

    @DeleteMapping(path = "/employee/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Integer id) {
        empService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    //TODO: replace it latter with JPQL
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getNamesAndIds() {

        List employers = empService.getAllEmpNamesIds();

        return ResponseEntity.status(HttpStatus.OK)
                .body(singletonMap("employers", employers));
    }
}

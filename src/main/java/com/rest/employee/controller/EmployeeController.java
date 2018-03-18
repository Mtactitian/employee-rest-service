package com.rest.employee.controller;

import com.rest.employee.model.dto.EmployeeDto;
import com.rest.employee.service.EmpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class EmployeeController {

    private Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    private EmpService empService;

    public EmployeeController(EmpService empService) {
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
}

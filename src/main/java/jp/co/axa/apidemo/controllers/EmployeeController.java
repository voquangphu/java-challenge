package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.CustomException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees")
    public List<Employee> getEmployees() {
        List<Employee> employees = employeeService.retrieveEmployees();
        return employees;
    }

    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable (name="employeeId") Long employeeId) {
        return employeeService.getEmployee(employeeId);
    }

    @PostMapping("/employees/add")
    public ResponseEntity saveEmployee(Employee employee) throws CustomException {
        if (employee.getId() != null) {
            throw new CustomException("id cannot be set manually");
        }
        employeeService.saveEmployee(employee);
        logger.info("Employee Id " + employee.getId() + " Saved Successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>("{\"message\": \"" + "Employee Id " + employee.getId() + " Saved Successfully" + "\"}", headers, HttpStatus.OK);
    }

    @DeleteMapping("/employees/delete/{employeeId}")
    public ResponseEntity deleteEmployee(@PathVariable (name="employeeId") @NotNull Long employeeId) throws CustomException {
        if (employeeService.getEmployee(employeeId) == null) {
            throw new CustomException("Employee id does not exist");
        }
        employeeService.deleteEmployee(employeeId);
        logger.info("Employee Id " + employeeId + " Deleted Successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>("{\"message\": \"" + "Employee Id " + employeeId + " Deleted Successfully" + "\"}", headers, HttpStatus.OK);
    }

    @PutMapping("/employees/update/{employeeId}")
    public ResponseEntity updateEmployee(Employee employee,
                               @PathVariable (name="employeeId") @NotNull Long employeeId) throws CustomException {
        if (employee.getId() != null && !employee.getId().equals(employeeId)) {
            throw new CustomException("Employee from request body and path is mismatched");
        }
        if (employeeService.getEmployee(employeeId) == null) {
            throw new CustomException("Employee id does not exist");
        }
        Employee emp = employeeService.getEmployee(employeeId);
        if(emp != null){
            employeeService.updateEmployee(employee);
        }
        logger.info("Employee Id " + employeeId + " Updated Successfully");

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return new ResponseEntity<>("{\"message\": \"" + "Employee Id " + employeeId + " Updated Successfully" + "\"}", headers, HttpStatus.OK);
    }

}

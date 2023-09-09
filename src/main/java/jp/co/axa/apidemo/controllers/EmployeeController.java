package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.CustomException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

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
    public void saveEmployee(Employee employee) throws CustomException {
        if (employee.getId() != null) {
            throw new CustomException("id cannot be set manually");
        }
        employeeService.saveEmployee(employee);
        System.out.println("Employee Saved Successfully");
    }

    @DeleteMapping("/employees/delete/{employeeId}")
    public void deleteEmployee(@PathVariable (name="employeeId") @NotNull Long employeeId) throws CustomException {
        if (employeeService.getEmployee(employeeId) == null) {
            throw new CustomException("Employee id does not exist");
        }
        employeeService.deleteEmployee(employeeId);
        System.out.println("Employee Deleted Successfully");
    }

    @PutMapping("/employees/update/{employeeId}")
    public void updateEmployee(Employee employee,
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

    }

}

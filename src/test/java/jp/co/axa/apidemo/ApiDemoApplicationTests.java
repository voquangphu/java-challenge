package jp.co.axa.apidemo;

import jp.co.axa.apidemo.controllers.EmployeeController;
import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exceptions.CustomException;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiDemoApplicationTests {

	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@Test
	public void testGetEmployees() {
		MockitoAnnotations.initMocks(this);

		List<Employee> result = new ArrayList<>();
		Employee employee1 = new Employee(new Long(1), "abc", 1, "xyz");
		Employee employee2 = new Employee(new Long(2), "bcd", 1, "xyz");
		result.add(employee1);
		result.add(employee2);

		Mockito.when(employeeService.retrieveEmployees()).thenReturn(result);

		List<Employee> controllerResult = employeeController.getEmployees();
		Assert.assertEquals(controllerResult.size(), 2);
	}

	@Test
	public void testAddEmployee() throws CustomException {
		MockitoAnnotations.initMocks(this);

		Employee employee1 = new Employee(null, "abc", 1, "xyz");
		ResponseEntity controllerResult = employeeController.saveEmployee(employee1);
		Assert.assertEquals(controllerResult.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testDeleteEmployee() throws CustomException {
		MockitoAnnotations.initMocks(this);

		Mockito.when(employeeService.getEmployee(new Long(1))).thenReturn(new Employee(new Long(1), "abc", 1, "xyz"));

		ResponseEntity controllerResult = employeeController.deleteEmployee(new Long(1));
		Assert.assertEquals(controllerResult.getStatusCode(), HttpStatus.OK);
	}

	@Test
	public void testUpdateEmployee() throws CustomException {
		MockitoAnnotations.initMocks(this);

		Mockito.when(employeeService.getEmployee(new Long(1))).thenReturn(new Employee(new Long(1), "abc", 1, "xyz"));

		Employee employee1 = new Employee(null, "abc", 1, "xyz");

		ResponseEntity controllerResult = employeeController.updateEmployee(employee1, new Long(1));
		Assert.assertEquals(controllerResult.getStatusCode(), HttpStatus.OK);
	}

}

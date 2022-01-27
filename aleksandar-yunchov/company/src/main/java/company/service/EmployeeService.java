package company.service;

import company.model.employee.Employee;
import company.model.employee.RegisterEmployeeRequest;

public interface EmployeeService
{
   Employee createEmployee(RegisterEmployeeRequest request);

   Employee getByUsername(String username);

   void changeRoleOfEmployee(Long empId, Long roleId);

   Employee getById(Long empId);
}

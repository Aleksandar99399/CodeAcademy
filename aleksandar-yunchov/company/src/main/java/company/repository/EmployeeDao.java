package company.repository;

import company.model.employee.Employee;

import java.util.Optional;

public interface EmployeeDao
{
   Employee createEmployee(Employee employeeFromRequest);

   Optional<Employee> getById(Long id);

   Optional<Employee> getByUsername(String username);

   void changeRoleOfEmployee(Long empId, Long roleId);
}

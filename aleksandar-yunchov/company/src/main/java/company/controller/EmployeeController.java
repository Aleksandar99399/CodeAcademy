package company.controller;

import company.model.employee.RegisterEmployeeRequest;
import company.service.EmployeeService;
import company.validation.group.ValidationSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@Validated(ValidationSequence.class)
//@PreAuthorize("hasRole('ADMIN')")
public class EmployeeController
{
   private final EmployeeService employeeService;

   @Autowired
   public EmployeeController(EmployeeService employeeService)
   {
      this.employeeService = employeeService;
   }

   @PostMapping
   public ResponseEntity<?> createEmployee(@RequestBody RegisterEmployeeRequest request){
      employeeService.createEmployee(request);
      return ResponseEntity.ok("Successfully registered user");
   }

   @PatchMapping("/{id}")
   public void changeRoleOfEmployee(@PathVariable("id") Long empId, @RequestParam Long roleId){
      employeeService.changeRoleOfEmployee(empId,roleId);
   }

}

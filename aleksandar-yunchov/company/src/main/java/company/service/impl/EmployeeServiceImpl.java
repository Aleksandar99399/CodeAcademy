package company.service.impl;

import company.exception.employee.EmployeeNotFoundException;
import company.model.Role;
import company.model.employee.Employee;
import company.model.employee.RegisterEmployeeRequest;
import company.repository.EmployeeDao;
import company.service.EmployeeService;
import company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService, UserDetailsService
{
   private final EmployeeDao     employeeDao;
   private final RoleService     roleService;
   private final PasswordEncoder passwordEncoder;


   @Autowired
   public EmployeeServiceImpl(EmployeeDao employeeDao, RoleService roleService, PasswordEncoder passwordEncoder
   )
   {
      this.employeeDao = employeeDao;
      this.roleService = roleService;
      this.passwordEncoder = passwordEncoder;
   }

   @Override
   public Employee createEmployee(RegisterEmployeeRequest request)
   {
      Employee employeeFromRequest;
      try {
         employeeFromRequest =
            new Employee()
               .setUsername(request.getUsername())
               .setPassword(passwordEncoder.encode(request.getPassword()))
               .setRoleId(2L);
         return employeeDao.createEmployee(employeeFromRequest);
      }catch (Exception ex) {
         throw new EmployeeNotFoundException("Employee already exist");
      }

   }

   @Override
   public Employee getByUsername(String username)
   {
      return employeeDao.getByUsername(username)
         .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
   }

   @Override
   public void changeRoleOfEmployee(Long empId, Long roleId)
   {
      roleService.getRoleById(roleId);
      this.getById(empId);
      employeeDao.changeRoleOfEmployee(empId,roleId);
   }

   @Override
   public Employee getById(Long empId)
   {
      return employeeDao.getById(empId)
         .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
   }

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
   {
      Employee employee = this.getByUsername(username);
      Role roleById = roleService.getRoleById(employee.getRoleId());
      User userDet = new User(
         employee.getUsername(),
         employee.getPassword(),

         Collections.singleton(new SimpleGrantedAuthority(roleById.getRoleType().toString()))
      );
      return userDet;
   }

}

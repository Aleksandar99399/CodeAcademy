package company.model.employee;

public class Employee
{
   private Long   employeeId;
   private String username;
   private String password;
   private Long roleId;

   public Employee()
   {
   }

   public Long getEmployeeId()
   {
      return employeeId;
   }

   public Employee setEmployeeId(Long employeeId)
   {
      this.employeeId = employeeId;
      return this;
   }

   public String getUsername()
   {
      return username;
   }

   public Employee setUsername(String username)
   {
      this.username = username;
      return this;
   }

   public String getPassword()
   {
      return password;
   }

   public Employee setPassword(String password)
   {
      this.password = password;
      return this;
   }

   public Long getRoleId()
   {
      return roleId;
   }

   public Employee setRoleId(Long roleId)
   {
      this.roleId = roleId;
      return this;
   }
}

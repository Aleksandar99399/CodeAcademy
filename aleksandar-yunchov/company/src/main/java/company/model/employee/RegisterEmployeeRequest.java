package company.model.employee;

import company.validation.Match;
import company.validation.StrongPassword;
import company.validation.group.SecondExecuteGroup;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Match(first = "password", second = "passwordConfirmation", groups = SecondExecuteGroup.class)
public class RegisterEmployeeRequest
{
   private String username;
   private String password;
   private String confirmPassword;

   public RegisterEmployeeRequest()
   {
   }

   @Pattern(regexp = "^[a-z0-9\\.\\-]+$", message = "Username is not valid! (a-z0-9.-)")
   @Length(min = 4, message = "Username length must be least 4 symbols")
   @NotBlank(message = "Username cannot be null or empty")
   public String getUsername()
   {
      return username;
   }

   public RegisterEmployeeRequest setUsername(String username)
   {
      this.username = username;
      return this;
   }

   @StrongPassword
   public String getPassword()
   {
      return password;
   }

   public RegisterEmployeeRequest setPassword(String password)
   {
      this.password = password;
      return this;
   }

   public String getConfirmPassword()
   {
      return confirmPassword;
   }

   public RegisterEmployeeRequest setConfirmPassword(String confirmPassword)
   {
      this.confirmPassword = confirmPassword;
      return this;
   }
}

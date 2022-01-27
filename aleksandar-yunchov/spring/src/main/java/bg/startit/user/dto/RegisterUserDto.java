package bg.startit.user.dto;


import bg.startit.validation.Match;
import bg.startit.validation.StrongPassword;
import bg.startit.validation.group.SecondExecuteGroup;
import bg.startit.validation.group.ThirdExecuteGroup;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.*;

@Match(groups = ThirdExecuteGroup.class)
public class RegisterUserDto
{
   private String firstName;
   private String lastName;
   private String email;
   private String password;
   private String confirmPassword;

   public RegisterUserDto()
   {
   }

   @Length(min = 3, message = "Името трябва да съдържа поне 3 символа")
   @Pattern(regexp = "^[А-я-A-z]+$", message = "Името не може да бъде празно")
   @NotEmpty(message = "Не можете да създадете user без име")
   public String getFirstName()
   {
      return firstName;
   }

   public RegisterUserDto setFirstName(String firstName)
   {
      this.firstName = firstName;
      return this;
   }

   @Length(min = 3, message = "Фамилията трябва да съдържа поне 3 символа")
   @Pattern(regexp = "^[А-я-A-z]+$")
   @NotEmpty(message = "Не можете да създадете user без фамилия")
   public String getLastName()
   {
      return lastName;
   }

   public RegisterUserDto setLastName(String lastName)
   {
      this.lastName = lastName;
      return this;
   }

   @Column(unique = true)
   @Email(message = "Невалиден имейл")
   @NotEmpty(message = "Не можете да създадете user без имейл")
   public String getEmail()
   {
      return email;
   }

   public RegisterUserDto setEmail(String email)
   {
      this.email = email;
      return this;
   }

   @Length(min = 4, message = "Паролата не може да съдържа по-малко от 4 символа")
   @NotNull(message = "Паролата не може да бъде null")
   @Pattern(regexp = "^(\\S+)$", message = "Паролата не може да съдържа интервали")
   @StrongPassword(groups = SecondExecuteGroup.class)
   public String getPassword()
   {
      return password;
   }

   public RegisterUserDto setPassword(String password)
   {
      this.password = password;
      return this;
   }

   @Pattern(regexp = "^[a-z0-9\\.\\-]+$", message = "Username is not valid! (a-z0-9.-)")
   @Length(min = 4, message = "Username length must be least 4 symbols")
   @NotBlank()
   @StrongPassword(groups = SecondExecuteGroup.class)
   public String getConfirmPassword()
   {
      return confirmPassword;
   }

   public RegisterUserDto setConfirmPassword(String confirmPassword)
   {
      this.confirmPassword = confirmPassword;
      return this;
   }


}

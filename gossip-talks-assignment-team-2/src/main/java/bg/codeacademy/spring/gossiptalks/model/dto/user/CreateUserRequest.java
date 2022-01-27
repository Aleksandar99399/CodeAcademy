package bg.codeacademy.spring.gossiptalks.model.dto.user;

import bg.codeacademy.spring.gossiptalks.validation.Match;
import bg.codeacademy.spring.gossiptalks.validation.StrongPassword;
import bg.codeacademy.spring.gossiptalks.validation.group.SecondExecuteGroup;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Match(first = "password", second = "passwordConfirmation", groups = SecondExecuteGroup.class)
public class CreateUserRequest
{
   private String email;
   private String username;
   private String name;
   private String password;
   private String passwordConfirmation;

   public CreateUserRequest(String email, String username, String name, String password, String passwordConfirmation)
   {
      this.email = email;
      this.username = username;
      this.name = name;
      this.password = password;
      this.passwordConfirmation = passwordConfirmation;
   }

   @Email(message = "Email is not valid")
   public String getEmail()
   {
      return email;
   }

   public CreateUserRequest setEmail(String email)
   {
      this.email = email;
      return this;
   }

   @Pattern(regexp = "^[a-z0-9\\.\\-]+$", message = "Username is not valid! (a-z0-9.-)")
   @Length(min = 4, message = "Username length must be least 4 symbols")
   @NotNull
   @Column(unique = true)
   public String getUsername()
   {
      return username;
   }

   public CreateUserRequest setUsername(String username)
   {
      this.username = username;
      return this;
   }

   @Length(max = 50, message = "Name is too long")
   public String getName()
   {
      return name;
   }

   public CreateUserRequest setName(String name)
   {
      this.name = name;
      return this;
   }

   @StrongPassword
   public String getPassword()
   {
      return password;
   }

   public CreateUserRequest setPassword(String password)
   {
      this.password = password;
      return this;
   }

   public String getPasswordConfirmation()
   {
      return passwordConfirmation;
   }

   public CreateUserRequest setPasswordConfirmation(String passwordConfirmation)
   {
      this.passwordConfirmation = passwordConfirmation;
      return this;
   }

   @Override
   public String toString()
   {
      return "CreateUserRequest{" +
         "email='" + email + '\'' +
         ", username='" + username + '\'' +
         ", name='" + name + '\'' +
         ", password='" + password + '\'' +
         ", passwordConfirmation='" + passwordConfirmation + '\'' +
         '}';
   }
}

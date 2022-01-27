package bg.startit.user.dto;


import bg.startit.role.dto.RegisterRoleDto;
import bg.startit.validation.StrongPassword;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

public class UpdateUserDto
{

   private Long id;

   @Length(min = 3, message = "First name must be least 3 symbols")
   @Pattern(regexp = "^[А-Яа-я ]+$")
   @NotEmpty
   private String firstName;

   @Length(min = 3, message = "Last name must be least 3 symbols")
   @Pattern(regexp = "^[А-Яа-я ]+$")
   @NotEmpty
   private String lastName;

   private List<RegisterRoleDto> roles = new ArrayList<>();

   @Column(unique = true)
   @Email(message = "Email is not valid!")
   @NotEmpty
   private String email;

   @Length(min = 4, message = "Password must be least 4 symbols")
   @StrongPassword
   private String password;

   public UpdateUserDto()
   {
   }


   public List<RegisterRoleDto> getRoles()
   {
      return roles;
   }

   public UpdateUserDto setRoles(List<RegisterRoleDto> roles)
   {
      this.roles = roles;
      return this;
   }

   public String getFirstName()
   {
      return firstName;
   }

   public void setFirstName(String firstName)
   {
      this.firstName = firstName;
   }

   public String getLastName()
   {
      return lastName;
   }

   public void setLastName(String lastName)
   {
      this.lastName = lastName;
   }

   public String getEmail()
   {
      return email;
   }

   public void setEmail(String email)
   {
      this.email = email;
   }

   public String getPassword()
   {
      return password;
   }

   public void setPassword(String password)
   {
      this.password = password;
   }

   public Long getId()
   {
      return id;
   }

   public UpdateUserDto setId(Long id)
   {
      this.id = id;
      return this;
   }
}

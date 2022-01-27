package bg.startit.user.dto;


import bg.startit.role.Role;

import java.util.List;

public class ResponseUser
{
   private String     firstName;
   private String     lastName;
   private String     email;
   private List<Role> roles;

   public ResponseUser()
   {
   }

   public String getFirstName()
   {
      return firstName;
   }

   public ResponseUser setFirstName(String firstName)
   {
      this.firstName = firstName;
      return this;
   }

   public String getLastName()
   {
      return lastName;
   }

   public ResponseUser setLastName(String lastName)
   {
      this.lastName = lastName;
      return this;
   }

   public String getEmail()
   {
      return email;
   }

   public ResponseUser setEmail(String email)
   {
      this.email = email;
      return this;
   }

   public List<Role> getRoles()
   {
      return roles;
   }

   public ResponseUser setRoles(List<Role> roles)
   {
      this.roles = roles;
      return this;
   }
}

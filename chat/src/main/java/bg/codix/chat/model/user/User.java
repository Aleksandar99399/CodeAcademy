package bg.codix.chat.model.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class User
{
   private Long id;

   private String username;
   private String firstName;
   private String lastName;

   public User()
   {
   }

   public User(Long id, String username, String firstName, String lastName)
   {
      this.id = id;
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
   }

   public Long getId()
   {
      return id;
   }

   public User setId(Long id)
   {
      this.id = id;
      return this;
   }

   @NotBlank(message = "Username cannot be null or empty field")
   @Length(min = 4, max = 20, message = "Username is too long or short")
   @Pattern(regexp = "[A-z0-9-.]", message = "Invalid username. Hint:[A-Za-z0-9.-]")
   public String getUsername()
   {
      return username;
   }

   public User setUsername(String username)
   {
      this.username = username;
      return this;
   }

   @NotBlank(message = "First name cannot be null or empty field")
   @Length(min = 2, message = "Invalid first name")
   @Pattern(regexp = "[A-z]", message = "Invalid username first name")
   public String getFirstName()
   {
      return firstName;
   }

   public User setFirstName(String firstName)
   {
      this.firstName = firstName;
      return this;
   }

   @NotBlank(message = "Last name cannot be null or empty field")
   @Length(min = 2, message = "Invalid last name")
   @Pattern(regexp = "[A-z]", message = "Invalid username last name")
   public String getLastName()
   {
      return lastName;
   }

   public User setLastName(String lastName)
   {
      this.lastName = lastName;
      return this;
   }

   @Override
   public String toString()
   {
      return "User{" +
         "id=" + id +
         ", username='" + username + '\'' +
         ", first_name='" + firstName + '\'' +
         ", last_name='" + lastName + '\'' +
         '}';
   }

}

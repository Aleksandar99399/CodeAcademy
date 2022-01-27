package bg.startit.user;


import bg.startit.role.Role;
import bg.startit.validation.StrongPassword;
import bg.startit.validation.group.SecondExecuteGroup;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User
{
   private Long       id;
   private String     firstName;
   private String     lastName;
   private List<Role> roles = new ArrayList<>();
   private String     email;
   private String     password;

   public User()
   {
   }

   public User(String firstName, String lastName, String email, String password)
   {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
      this.password = password;
   }

   @Id
   @GeneratedValue
   public Long getId()
   {
      return id;
   }


   // С cascade= CascadeType.ALL - можем да запазваме ролите директно в базата Role
   @ManyToMany(cascade = {CascadeType.DETACH},fetch = FetchType.EAGER)
   @JoinTable(name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id"))
   @NotEmpty(message = "Клиентът трябва да има роля")
   public List<Role> getRoles()
   {
      return roles;
   }


   @Length(min = 3, message = "Името трябва да съдържа най-малко 3 символа")
   @Pattern(regexp = "^[А-я-A-z]+$", message = "Невалидни символи за име")
   @NotNull(message = "Името не може да бъде null")
   public String getFirstName()
   {
      return firstName;
   }



   @Length(min = 3, message = "Фамилията трябва да съдържа най-малко 3 символа")
   @Pattern(regexp = "^[А-я-A-z]+$", message = "Невалидни символи за фамилия")
   @NotNull(message = "Фамилията не може да бъде null")
   public String getLastName()
   {
      return lastName;
   }


   @Column(unique = true)
   @Email(message = "Невалиден имейл")
   @NotNull(message = "Имейлът не може да бъде null")
   public String getEmail()
   {
      return email;
   }


   @Length(min = 4, message = "Паролата не може да съдържа по-малко от 4 символа")
   @NotNull(message = "Паролата не може да бъде null")
   @Pattern(regexp = "^(\\S+)$", message = "Паролата не може да съдържа интервали")
   @StrongPassword(groups = SecondExecuteGroup.class)
   public String getPassword()
   {
      return password;
   }


   public User setId(Long id)
   {
      this.id = id;
      return this;
   }

   public User setFirstName(String firstName)
   {
      this.firstName = firstName;
      return this;
   }

   public User setLastName(String lastName)
   {
      this.lastName = lastName;
      return this;
   }

   public User setRoles(List<Role> roles)
   {
      this.roles = roles;
      return this;
   }

   public User setEmail(String email)
   {
      this.email = email;
      return this;
   }

   public User setPassword(String password)
   {
      this.password = password;
      return this;
   }
}

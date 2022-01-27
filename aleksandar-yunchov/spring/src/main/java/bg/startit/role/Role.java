package bg.startit.role;


import bg.startit.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Role implements GrantedAuthority
{
   @Override
   public String getAuthority()
   {
      return "ROLE_" + role_name.toString();
   }

   public enum ROLE_NAME
   {
      LIBRARIAN,
      USER,
      ADMIN
   }

   @Id
   @GeneratedValue
   private Long id;

   @Enumerated(EnumType.STRING)
   private ROLE_NAME role_name;

   @ManyToMany(mappedBy = "roles")
   @JsonIgnore
   private List<User> users = new ArrayList<>();

   public Role()
   {
   }

   public List<User> getUsers()
   {
      return users;
   }

   public void setUsers(List<User> users)
   {
      this.users = users;
   }

   public Long getId()
   {
      return id;
   }

   public void setId(Long id)
   {
      this.id = id;
   }

   public ROLE_NAME getRole_name()
   {
      return role_name;
   }

   public void setRole_name(ROLE_NAME role_name)
   {
      this.role_name = role_name;
   }
}

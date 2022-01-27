package company.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority
{
   @Override
   public String getAuthority()
   {
      return roleType.toString();
   }


   private Long roleId;
   private RoleType roleType;


   public Role()
   {
   }

   public Long getRoleId()
   {
      return roleId;
   }

   public Role setRoleId(Long roleId)
   {
      this.roleId = roleId;
      return this;
   }

   public RoleType getRoleType()
   {
      return roleType;
   }

   public Role setRoleType(RoleType roleType)
   {
      this.roleType = roleType;
      return this;
   }
}

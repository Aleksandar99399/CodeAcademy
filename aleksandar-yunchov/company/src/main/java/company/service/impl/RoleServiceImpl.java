package company.service.impl;

import company.exception.role.RoleNotFoundException;
import company.model.Role;
import company.model.RoleType;
import company.repository.RoleDao;
import company.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService
{
   private final RoleDao roleDao;

   @Autowired
   public RoleServiceImpl(RoleDao roleDao)
   {
      this.roleDao = roleDao;
   }


   @Override
   public Role createRole(String name)
   {
      String roleName;
      try {
          roleName = RoleType.valueOf(name.toUpperCase()).toString();
      }catch (Exception ex){
         throw new RoleNotFoundException("RoleType is invalid");
      }
      return roleDao.createRole(roleName);
   }

   @Override
   public Role getRoleById(Long id)
   {
      return roleDao.getRoleById(id)
         .orElseThrow(() -> new RoleNotFoundException("Role not found"));
   }


}

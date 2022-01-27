package company.service;

import company.model.Role;

public interface RoleService
{
   Role createRole(String name);

   Role getRoleById(Long id);
}

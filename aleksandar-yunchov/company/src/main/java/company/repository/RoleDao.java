package company.repository;

import company.model.Role;

import java.util.Optional;

public interface RoleDao
{
   Role createRole(String name);

   Optional<Role> getRoleById(Long id);
}

package company.repository.impl;

import company.exception.role.RoleNotFoundException;
import company.model.Role;
import company.model.RoleType;
import company.repository.RoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class RoleDaoImpl implements RoleDao
{

   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public RoleDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public Role createRole(String name)
   {
      String sql =
         "INSERT INTO ROLE (NAME) VALUES (:name)";
      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("name", name),
         keyHolder,
         new String[]{"role_id"}
      );

      return this.getRoleById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new RoleNotFoundException("Role not found"));
   }

   @Override
   public Optional<Role> getRoleById(Long id)
   {
      String sql =
         "SELECT ROLE_ID, NAME FROM ROLE WHERE ROLE_ID = :id";

      try {
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            (rs, row) ->
               new Role()
                  .setRoleId(rs.getLong("role_id"))
                  .setRoleType(rs.getObject("name", RoleType.class))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }
   }
}

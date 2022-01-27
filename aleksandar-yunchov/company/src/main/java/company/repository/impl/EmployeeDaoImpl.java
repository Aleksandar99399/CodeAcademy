package company.repository.impl;

import company.exception.employee.EmployeeNotFoundException;
import company.model.employee.Employee;
import company.repository.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class EmployeeDaoImpl implements EmployeeDao
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public EmployeeDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public Employee createEmployee(Employee request)
   {
      String sql =
         "INSERT INTO                                             "
        +"  REQUEST_FINANCING.EMPLOYEE (USERNAME,PASSWORD,ROLE_ID)"
        +"VALUES (:username, :password, :roleId)                  ";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("username", request.getUsername())
            .addValue("password", request.getPassword())
            .addValue("roleId", request.getRoleId()),
         keyHolder,
         new String[]{"employee_id"}
      );

      return this.getById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

   }
   @Override
   public Optional<Employee> getById(Long id)
   {
      String sql =
         "SELECT EMPLOYEE_ID, USERNAME, PASSWORD, ROLE_ID  "
            +" FROM REQUEST_FINANCING.EMPLOYEE                 "
            +"WHERE EMPLOYEE_ID = :id                       ";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            (rs, row) ->
               new Employee()
                  .setEmployeeId(rs.getLong("employee_id"))
                  .setUsername(rs.getString("username"))
                  .setPassword(rs.getString("password"))
                  .setRoleId(rs.getLong("role_id"))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }

   }

   @Override
   public Optional<Employee> getByUsername(String username)
   {
      String sql =
             "SELECT EMPLOYEE_ID, USERNAME, PASSWORD, ROLE_ID  "
            +" FROM REQUEST_FINANCING.EMPLOYEE                 "
            +"WHERE USERNAME = :username                       ";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("username", username),
            (rs, row) ->
               new Employee()
                  .setEmployeeId(rs.getLong("employee_id"))
                  .setUsername(rs.getString("username"))
                  .setPassword(rs.getString("password"))
                  .setRoleId(rs.getLong("role_id"))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }

   }

   @Override
   public void changeRoleOfEmployee(Long empId, Long roleId)
   {
      String sql =
         "UPDATE REQUEST_FINANCING.EMPLOYEE SET ROLE_ID = :roleId WHERE EMPLOYEE_ID = :empId";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("roleId", roleId)
            .addValue("empId", empId)
      );
   }
}

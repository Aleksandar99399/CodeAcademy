package company.repository.impl;

import company.exception.company.CompanyNotFoundException;
import company.model.company.Company;
import company.repository.CompanyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CompanyDaoImpl implements CompanyDao
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public CompanyDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public List<Company> getAllCompanies(){
      String sql = "SELECT company_id, name FROM COMPANIES";

      return namedParameterJdbcTemplate.query(
         sql,
         (rs, row) ->
            new Company()
               .setCompanyId(rs.getLong("company_id"))
               .setName(rs.getString("name"))
      );
   }

   @Override
   public Optional<Company> getCompanyById(Long id){
      String sql = "SELECT company_id, name FROM COMPANIES WHERE COMPANY_ID = :id";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            (rs, row) ->
               new Company()
                  .setCompanyId(rs.getLong("company_id"))
                  .setName(rs.getString("name")))
         );
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }
   }

   @Override
   public Company createCompany(String name)
   {
      String sql =
         "INSERT INTO COMPANIES (name) VALUES (:name)";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("name", name),
         keyHolder,
         new String[]{"company_Id"}
      );

      return getCompanyById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
   }

   @Override
   public Company updateCompany(Long id, Company company)
   {
      String sql =
          "UPDATE COMPANIES         "
         +" SET name = :companyName "
         +"WHERE COMPANY_ID = :id   ";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("companyName", company.getName())
            .addValue("id", id),
         keyHolder,
         new String[]{"company_id"}
         );

      return getCompanyById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new CompanyNotFoundException("Company not found"));
   }

   @Override
   public void deleteCompany(Long id)
   {
      String sql = "DELETE FROM COMPANIES WHERE COMPANY_ID = :id";

      namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource("id", id));
   }
}

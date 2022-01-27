package company.repository.impl;

import company.exception.individual.IndividualNotFoundException;
import company.model.TypeEntity;
import company.model.individual.Individual;
import company.model.individual.IndividualByCriteriaResponse;
import company.model.individual.UpdateIndividualRequest;
import company.repository.IndividualDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class IndividualDaoImpl implements IndividualDao
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public IndividualDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public List<Individual> getAllIndividuals()
   {
      String sql =
         "SELECT INDIV_ID, name, type, COMPANY_ID FROM INDIVIDUAL";

      return namedParameterJdbcTemplate.query(
         sql,
         (rs, row) ->
            new Individual()
               .setIndivId(rs.getLong("indiv_id"))
               .setName(rs.getString("name"))
               .setTypeEntity(rs.getObject("type", TypeEntity.class))
               .setCompanyId(rs.getLong("company_id"))
      );
   }

   @Override
   public Optional<Individual> getIndividualById(Long id)
   {
      String sql =
            "SELECT INDIV_ID, name, type, COMPANY_ID"
           +" FROM INDIVIDUAL                       "
           +"WHERE INDIV_ID = :id                   ";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            (rs, row) ->
               new Individual()
                  .setIndivId(rs.getLong("indiv_id"))
                  .setName(rs.getString("name"))
                  .setTypeEntity(rs.getObject("type", TypeEntity.class))
                  .setCompanyId(rs.getLong("company_id"))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }
   }

   @Override
   public Individual createIndividual(Individual individual)
   {
      String sql = "INSERT INTO INDIVIDUAL (name, type, COMPANY_ID) VALUES (:name, :type, :companyId)";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("name", individual.getName())
            .addValue("type", individual.getTypeEntity().toString())
            .addValue("companyId", individual.getCompanyId()),
         keyHolder,
         new String[]{"indiv_id"}
      );

      return this.getIndividualById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new IndividualNotFoundException("Individual not found"));
   }

   @Override
   public Individual updateIndividual(Long indivId, UpdateIndividualRequest request)
   {
      String sql =
         "UPDATE INDIVIDUAL                             "
        +"    SET NAME = NVL(:name, NAME),              "
        +"        TYPE = NVL(:type,TYPE),               "
        +"        COMPANY_ID = NVL(:companyId, COMPANY_ID)"
        +" WHERE INDIV_ID = :id                          ";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("name", request.getName())
            .addValue("type", request.getTypeEntity())
            .addValue("companyId", request.getCompanyId())
            .addValue("id", indivId),
         keyHolder,
         new String[]{"indiv_id"}
      );

      return this.getIndividualById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new IndividualNotFoundException("Individual not found"));
   }

   @Override
   public void deleteIndividual(Long indivId)
   {
      String sql =
         "DELETE FROM INDIVIDUAL WHERE INDIV_ID = :id";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("id", indivId)
      );
   }

   @Override
   public List<IndividualByCriteriaResponse> getAllIndividualsByCriteria(Pageable pageable,
                                                                         String regex)
   {
      String sort = pageable.getSort().toString().replaceAll(": ", " ");
      String sql =
              "SELECT INDIV.NAME,                                     "
             +"       INDIV.TYPE,                                     "
             +"       R.EXT_REF,                                      "
             +"       INV.AMOUNT,                                     "
             +"       INV.ISSUE_DATE,                                 "
             +"       C2.NAME company_name                            "
             +"FROM INDIVIDUAL INDIV,                                 "
             +"     COMPANIES C2,                                     "
             +"     INVOICES INV,                                     "
             +"     REFERENCES R                                      "
             +"WHERE C2.COMPANY_ID(+) = INDIV.COMPANY_ID              "
             +"  AND INV.INDIV_ID(+) = INDIV.INDIV_ID                 "
             +"  AND R.INDIV_ID(+) = INDIV.INDIV_ID                   "
             +  regex
             +" ORDER BY                                              "
                 + sort
             +"   OFFSET                                              "
             +       pageable.getPageNumber() * pageable.getPageSize()
             +"   ROWS FETCH NEXT                                     "
             +        pageable.getPageSize() + " ROWS ONLY            ";


      return namedParameterJdbcTemplate.query(
         sql,
         (rs, row) ->
            new IndividualByCriteriaResponse()
               .setIndividualName(rs.getString("NAME"))
               .setIndividualType(rs.getString("TYPE"))
               .setExternalId(rs.getObject("EXT_REF", Long.class))
               .setAmount(rs.getBigDecimal("AMOUNT"))
               .setIssueDate(rs.getObject("ISSUE_DATE", ZonedDateTime.class))
               .setCompanyName(rs.getString("company_name"))
      );
   }
}

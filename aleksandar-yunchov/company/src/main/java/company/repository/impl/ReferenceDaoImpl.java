package company.repository.impl;

import company.exception.reference.ReferenceNotFoundException;
import company.model.reference.Reference;
import company.repository.ReferenceDao;
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
public class ReferenceDaoImpl implements ReferenceDao
{

   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public ReferenceDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public List<Reference> getAll()
   {
      String sql =
         "SELECT ext_ref, indiv_id FROM REFERENCES";

      return namedParameterJdbcTemplate.query(
         sql,
         (rs, row) ->
            new Reference()
               .setExternalRef(rs.getLong("ext_ref"))
               .setIndivId(rs.getLong("indiv_id"))
      );
   }

   @Override
   public Optional<Reference> getReferenceByExtId(Long id)
   {
      String sql =
         "SELECT ext_ref, indiv_id FROM REFERENCES WHERE ext_ref = :extId";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("extId", id),
            (rs, row) ->
               new Reference()
                  .setExternalRef(rs.getLong("ext_ref"))
                  .setIndivId(rs.getLong("indiv_id"))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }
   }

   @Override
   public Reference createReference(Long indivId)
   {
      String sql =
         "INSERT INTO REFERENCES (indiv_id) VALUES (:indivId)";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("indivId", indivId),
         keyHolder,
         new String[]{"ext_ref"}
      );

      return this.getReferenceByExtId(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new ReferenceNotFoundException("Reference not found"));
   }

   @Override
   public Reference changeIndivIdOfReference(Long extId, Long indivId)
   {
      String sql =
         "UPDATE REFERENCES SET indiv_id = :indivId WHERE ext_ref = :extId";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource()
            .addValue("extId", extId)
            .addValue("indivId", indivId),
         keyHolder,
         new String[]{"ext_ref"}
      );

      return this.getReferenceByExtId(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new ReferenceNotFoundException("Reference not found"));
   }

   @Override
   public void deleteReference(Long extId)
   {
      String sql =
         "DELETE FROM REFERENCES WHERE ext_ref = :extId";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("extId", extId)
      );
   }
}

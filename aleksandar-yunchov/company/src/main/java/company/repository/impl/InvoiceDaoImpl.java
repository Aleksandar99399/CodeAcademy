package company.repository.impl;

import company.exception.invoice.InvoiceNotFoundException;
import company.model.invoice.Invoice;
import company.model.invoice.InvoiceEachCompanyResponse;
import company.repository.InvoiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class InvoiceDaoImpl implements InvoiceDao
{
   private final NamedParameterJdbcOperations namedParameterJdbcTemplate;
   private final KeyHolder keyHolder;

   @Autowired
   public InvoiceDaoImpl(NamedParameterJdbcOperations namedParameterJdbcTemplate, KeyHolder keyHolder)
   {
      this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
      this.keyHolder = keyHolder;
   }

   @Override
   public List<Invoice> getAllInvoices()
   {
      String sql =
         "SELECT invoice_id, amount, issue_date, indiv_id FROM INVOICES";

      return namedParameterJdbcTemplate.query(
         sql,
         (rs, row) ->
            new Invoice()
               .setInvoiceId(rs.getLong("invoice_id"))
               .setAmount(rs.getBigDecimal("amount"))
               .setIssueDate(rs.getObject("issue_date", ZonedDateTime.class))
               .setIndivId(rs.getLong("indiv_id"))
      );
   }

   @Override
   public Optional<Invoice> getInvoiceById(Long id)
   {
      String sql = "SELECT invoice_id, amount, issue_date, indiv_id FROM INVOICES WHERE invoice_id = :id";

      try {
         return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(
            sql,
            new MapSqlParameterSource("id", id),
            (rs, row) ->
               new Invoice()
                  .setInvoiceId(rs.getLong("invoice_id"))
                  .setAmount(rs.getBigDecimal("amount"))
                  .setIssueDate(rs.getObject("issue_date", ZonedDateTime.class))
                  .setIndivId(rs.getLong("indiv_id"))
         ));
      }catch (EmptyResultDataAccessException ex){
         return Optional.empty();
      }

   }

   @Override
   public Invoice decreaseAmount(Long invoiceId, BigDecimal amount)
   {
      String sql =
         "UPDATE INVOICES SET amount = amount - :amount WHERE invoice_id = :invoiceId";
      try {
         namedParameterJdbcTemplate.update(
            sql,
            new MapSqlParameterSource()
               .addValue("amount", amount)
               .addValue("invoiceId", invoiceId),
            keyHolder,
            new String[]{"invoice_id"}
         );
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }

      return this.getInvoiceById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
   }

   @Override
   public void deleteInvoiceById(Long invoiceId)
   {
      String sql =
         "DELETE FROM INVOICES WHERE invoice_id = :invoiceId";

      namedParameterJdbcTemplate.update(
         sql,
         new MapSqlParameterSource("invoiceId", invoiceId)
      );
   }

   @Override
   public Invoice createInvoice(Invoice request)
   {
      String sql =
         "INSERT INTO INVOICES (AMOUNT, ISSUE_DATE, INDIV_ID) VALUES (:amount, :issueDate, :indivId)";

      try {
         namedParameterJdbcTemplate.update(
            sql,
            new MapSqlParameterSource()
               .addValue("amount",request.getAmount())
               .addValue("issueDate", request.getIssueDate())
               .addValue("indivId",request.getIndivId()),
            keyHolder,
            new String[]{"invoice_id"}
         );
      }catch (Exception ex){
         System.out.println(ex.getMessage());
      }


      return this.getInvoiceById(Objects.requireNonNull(keyHolder.getKey()).longValue())
         .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
   }

   @Override
   public List<InvoiceEachCompanyResponse> getAmountForEachCompany()
   {
      String sql =
          "SELECT SUM(amount) sum_of_invoices,        "
         +"       COMP.NAME company_name              "
         +"FROM INVOICES INV,                         "
         +"     INDIVIDUAL INDIV,                     "
         +"     COMPANIES COMP                        "
         +"WHERE INV.INDIV_ID = INDIV.INDIV_ID        "
         +"    AND INDIV.COMPANY_ID = COMP.COMPANY_ID "
         +"    AND INV.ISSUE_DATE < :now              "
         +"GROUP BY COMP.NAME                         ";

      return namedParameterJdbcTemplate.query(
         sql,
         new MapSqlParameterSource("now", ZonedDateTime.now()),
         (rs, row) ->
            new InvoiceEachCompanyResponse()
               .setBigDecimal(rs.getBigDecimal("sum_of_invoices"))
               .setCompany(rs.getString("company_name"))
      );
   }
}

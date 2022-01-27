package company.repository;

import company.model.invoice.Invoice;
import company.model.invoice.InvoiceEachCompanyResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface InvoiceDao
{
   Invoice createInvoice(Invoice request);

   List<Invoice> getAllInvoices();

   Optional<Invoice> getInvoiceById(Long id);

   Invoice decreaseAmount(Long indivId, BigDecimal amount);

   void deleteInvoiceById(Long indivId);

   List<InvoiceEachCompanyResponse> getAmountForEachCompany();
}

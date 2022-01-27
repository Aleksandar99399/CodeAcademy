package company.service;

import company.model.invoice.Invoice;
import company.model.invoice.InvoiceEachCompanyResponse;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService
{
   Invoice createInvoice(Invoice request);

   List<Invoice> getAllInvoices();

   Invoice getInvoiceById(Long id);

   Invoice decreaseAmount(Long indivId, BigDecimal amount);

   void deleteInvoiceById(Long indivId);

   List<InvoiceEachCompanyResponse> getAmountForEachCompany();
}

package company.service.impl;

import company.exception.invoice.InvoiceNotFoundException;
import company.model.invoice.Invoice;
import company.model.invoice.InvoiceEachCompanyResponse;
import company.repository.InvoiceDao;
import company.service.IndividualService;
import company.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService
{
   private final InvoiceDao invoiceDao;
   private final IndividualService individualService;

   @Autowired
   public InvoiceServiceImpl(InvoiceDao invoiceDao, IndividualService individualService)
   {
      this.invoiceDao = invoiceDao;
      this.individualService = individualService;
   }

   @Override
   public List<Invoice> getAllInvoices()
   {
      return invoiceDao.getAllInvoices();
   }

   @Override
   public Invoice getInvoiceById(Long id)
   {
      return invoiceDao.getInvoiceById(id)
         .orElseThrow(() -> new InvoiceNotFoundException("Invoice not found"));
   }

   @Override
   public Invoice decreaseAmount(Long invoiceId, BigDecimal amount)
   {
      this.getInvoiceById(invoiceId);
      return invoiceDao.decreaseAmount(invoiceId, amount);

   }

   @Override
   public void deleteInvoiceById(Long invoiceId)
   {
      this.getInvoiceById(invoiceId);
      invoiceDao.deleteInvoiceById(invoiceId);
   }

   @Override
   public Invoice createInvoice(Invoice request)
   {
      individualService.getIndividualById(request.getIndivId());
      request.setIssueDate(ZonedDateTime.now());
      return invoiceDao.createInvoice(request);

   }

   @Override
   public List<InvoiceEachCompanyResponse> getAmountForEachCompany()
   {
      return invoiceDao.getAmountForEachCompany();
   }

}

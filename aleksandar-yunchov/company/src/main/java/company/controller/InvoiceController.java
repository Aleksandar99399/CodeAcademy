package company.controller;

import company.model.invoice.Invoice;
import company.model.invoice.InvoiceEachCompanyResponse;
import company.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@Validated
public class InvoiceController {

   private final InvoiceService invoiceService;

   @Autowired
   public InvoiceController(InvoiceService invoiceService)
   {
      this.invoiceService = invoiceService;
   }


   @GetMapping
   public ResponseEntity<List<Invoice>> getAllInvoices(){
      return ResponseEntity.ok(invoiceService.getAllInvoices());
   }

   @GetMapping("/{id}")
   public ResponseEntity<Invoice> getInvoiceById(@PathVariable("id") Long id){
      return ResponseEntity.ok(invoiceService.getInvoiceById(id));
   }

   @PostMapping
   public ResponseEntity<Invoice> createInvoice(@Valid @RequestBody Invoice request){
      return ResponseEntity.ok(invoiceService.createInvoice(request));
   }

   @PatchMapping("/{id}/decrease_amount")
   public ResponseEntity<Invoice> decreaseAmountByInvoiceId(@PathVariable("id") Long invoiceId,
                                                         @DecimalMin(value = "0") @RequestParam BigDecimal amount){
      return ResponseEntity.ok(invoiceService.decreaseAmount(invoiceId, amount));
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<String> deleteInvoiceById(@PathVariable("id") Long invoiceId){
      invoiceService.deleteInvoiceById(invoiceId);
      return ResponseEntity.ok("Successfully deleted");
   }

   @GetMapping("/amount_by_company")
   public ResponseEntity<List<InvoiceEachCompanyResponse>> getAmountForEachCompany(){
      return ResponseEntity.ok(invoiceService.getAmountForEachCompany());
   }

}

package company.exception;


import company.exception.company.CompanyInvalidNameException;
import company.exception.company.CompanyNotFoundException;
import company.exception.employee.EmployeeNotFoundException;
import company.exception.individual.IndividualNotFoundException;
import company.exception.invoice.InvoiceNotFoundException;
import company.exception.reference.ReferenceNotFoundException;
import company.exception.role.RoleNotFoundException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

   @ExceptionHandler(SortTypeInvalidException.class)
   public ResponseEntity<List<String>> handleSortTypeInvalidException(SortTypeInvalidException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(EmployeeNotFoundException.class)
   public ResponseEntity<List<String>> handleEmployeeNotFoundException(EmployeeNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(RoleNotFoundException.class)
   public ResponseEntity<List<String>> handleRoleNotFoundException(RoleNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(ReferenceNotFoundException.class)
   public ResponseEntity<List<String>> handleReferenceNotFoundException(ReferenceNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(CompanyInvalidNameException.class)
   public ResponseEntity<List<String>> handleCompanyInvalidNameException(CompanyInvalidNameException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(InvoiceNotFoundException.class)
   public ResponseEntity<List<String>> handleInvoiceNotFoundException(InvoiceNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(CompanyNotFoundException.class)
   public ResponseEntity<List<String>> handleCompanyNotFoundException(CompanyNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(IndividualNotFoundException.class)
   public ResponseEntity<List<String>> handleIndividualNotFoundException(IndividualNotFoundException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(InvalidTypeEntityException.class)
   public ResponseEntity<List<String>> handleInvalidTypeEntityException(InvalidTypeEntityException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @Override
   protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
   {
      Map<Object, String> errorMessage = new HashMap<>();
      errorMessage.put(ex.getValue(), "Require type is Number or wrong path for query");
      return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
   }

   private ResponseEntity<List<String>> getListResponseEntityWithEx(String message)
   {
      List<String> errorMessages = new ArrayList<>();
      errorMessages.add(message);
      return ResponseEntity.badRequest().body(errorMessages);
   }

   @Override
   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
   {
      System.out.println("In Method");
      Map<String, String> errorMessages = new HashMap<>();
      List<ObjectError> errors = ex.getBindingResult().getAllErrors();
      for (ObjectError error : errors) {
         String fieldOrObject = "";
         if (error instanceof FieldError) {
            fieldOrObject = ((FieldError) error).getField();
         }
         else if (error instanceof ObjectError) {
            fieldOrObject = error.getObjectName();
         }
         String errorMessage = error.getDefaultMessage();
         errorMessages.put(fieldOrObject, errorMessage);
      }
      return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
   }

   //когато имаме exception с @RequestParam
   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException ex)
   {
      Map<String, Object> body = new LinkedHashMap<>();

      System.out.println("In Constraint");

      for (ConstraintViolation<?> constEx : ex.getConstraintViolations()) {
         body.put(constEx.getPropertyPath().toString(), constEx.getMessage());
      }
      body.put("timestamp: ", LocalDateTime.now());
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

   }
}

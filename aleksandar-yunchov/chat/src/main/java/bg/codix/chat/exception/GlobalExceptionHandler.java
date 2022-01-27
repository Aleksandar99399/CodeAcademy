package bg.codix.chat.exception;

import bg.codix.chat.model.message.SendMessageRequest;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{

   @ExceptionHandler(MessageNotFoundException.class)
   public ResponseEntity<String> handleMessageNotFoundException(MessageNotFoundException ex)
   {
      return getResponseEntity(ex.getMessage());
   }

   @ExceptionHandler(UserNotFoundException.class)
   public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex)
   {
      return getResponseEntity(ex.getMessage());
   }

   @ExceptionHandler(GroupNotSameException.class)
   public ResponseEntity<String> handleGroupNotSameException(GroupNotSameException ex)
   {
      return getResponseEntity(ex.getMessage());
   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException ex)
   {
      Map<String, Object> body = new LinkedHashMap<>();

      System.out.println("In Constraint");

      for (ConstraintViolation<?> constEx : ex.getConstraintViolations()) {
         if (constEx.getLeafBean() instanceof SendMessageRequest) {
            SendMessageRequest dto = (SendMessageRequest) constEx.getLeafBean();
            body.put(dto.getClass().getSimpleName(), constEx.getMessage());
         }
         else {
            body.put(constEx.getPropertyPath().toString(), constEx.getMessage());
         }
      }
      body.put("timestamp: ", LocalDateTime.now());
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

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

   @Override
   protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
   {
      Map<Object, String> errorMessage = new HashMap<>();
      System.out.println("In Type");
      errorMessage.put(ex.getValue(), "Require type is Number");
      return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
   }

   private ResponseEntity<String> getResponseEntity(String message)
   {
      return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
   }
}
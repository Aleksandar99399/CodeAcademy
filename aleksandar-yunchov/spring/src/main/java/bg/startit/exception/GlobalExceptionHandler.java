package bg.startit.exception;


import bg.startit.exception.global.NotFoundException;
import bg.startit.exception.userexceptions.ExistUserException;
import bg.startit.exception.userexceptions.InvalidUserRoleException;
import bg.startit.user.dto.RegisterUserDto;
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

   @ExceptionHandler(ExistUserException.class)
   public ResponseEntity<String> handleExistUserEx(ExistUserException ex)
   {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }

   @ExceptionHandler(InvalidUserRoleException.class)
   public ResponseEntity<String> handleInvalidRoleEx(InvalidUserRoleException ex)
   {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(NotFoundException.class)
   public ResponseEntity<String> handleNotFoundEx(NotFoundException ex)
   {
      return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
   }

   // Изпълнява се когато нямаме анотацията @Valid
   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<Map<String, Object>> handleConstraintExceptions(ConstraintViolationException ex)
   {
      Map<String, Object> body = new LinkedHashMap<>();

      System.out.println("In Constraint");

      for (ConstraintViolation<?> constEx : ex.getConstraintViolations()) {
         if (constEx.getLeafBean() instanceof RegisterUserDto) {
            RegisterUserDto dto = (RegisterUserDto) constEx.getLeafBean();
            body.put(dto.getClass().getSimpleName(), constEx.getMessage());
         }
         else {
            body.put(constEx.getPropertyPath().toString(), constEx.getMessage());
         }
      }
      body.put("timestamp: ", LocalDateTime.now());
      return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);

   }


   //@Valid - изпълнява се този метод когато имаме тази анотация
//   @Override
//   protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request)
//   {
//      System.out.println("In MethodArgument");
//
//      Map<String, String> errorMessages = new HashMap<>();
//
//      List<ObjectError> errors = ex.getBindingResult().getAllErrors();
//      for(ObjectError error:errors){
//         String field = ((FieldError)error).getField();
//         String message = error.getDefaultMessage();
//         errorMessages.put(field,message);
//
//      }
//      return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
//   }

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


}

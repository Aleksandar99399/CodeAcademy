package bg.codeacademy.spring.gossiptalks.exception;


import bg.codeacademy.spring.gossiptalks.exception.userexceptions.ExistUserException;
import bg.codeacademy.spring.gossiptalks.exception.userexceptions.FollowUserException;
import bg.codeacademy.spring.gossiptalks.exception.userexceptions.PasswordsNotMatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler
{
   @ExceptionHandler(FollowUserException.class)
   public ResponseEntity<List<String>> handleFollowUserException(FollowUserException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(ExistUserException.class)
   public ResponseEntity<List<String>> handleExistUserEx(ExistUserException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(PasswordsNotMatchException.class)
   public ResponseEntity<List<String>> handlePasswordNotMatch(PasswordsNotMatchException ex)
   {
      return getListResponseEntityWithEx(ex.getMessage());
   }

   @ExceptionHandler(ConstraintViolationException.class)
   public ResponseEntity<List<String>> handleConstraintExceptions(ConstraintViolationException ex)
   {
      System.out.println("In Constraint");
      List<String> errorMessage = new ArrayList<>();
      for (ConstraintViolation<?> constEx : ex.getConstraintViolations()) {
         errorMessage.add(constEx.getMessage());
      }
      return ResponseEntity.badRequest().body(errorMessage);

   }

   private ResponseEntity<List<String>> getListResponseEntityWithEx(String message)
   {
      List<String> errorMessages = new ArrayList<>();
      errorMessages.add(message);
      return ResponseEntity.badRequest().body(errorMessages);
   }


}

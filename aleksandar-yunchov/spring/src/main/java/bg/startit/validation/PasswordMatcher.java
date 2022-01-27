package bg.startit.validation;


import bg.startit.user.dto.RegisterUserDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<Match, RegisterUserDto>
{
   @Override
   public void initialize(Match constraintAnnotation)
   {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }

   //Може да се направи да бъде field и да се поставя над confirmPassword
   @Override
   public boolean isValid(RegisterUserDto userRegisterRequest, ConstraintValidatorContext context)
   {

      // Правим exception който може да се каства към FieldError
      //         context
      //            .buildConstraintViolationWithTemplate(message)
      //            .addPropertyNode(second)
      //            .addConstraintViolation()
      //            .buildConstraintViolationWithTemplate(message)
      //            .addPropertyNode(first)
      //            .addConstraintViolation()
      //            .disableDefaultConstraintViolation();

      return userRegisterRequest.getPassword().equals(userRegisterRequest.getConfirmPassword());
   }
}
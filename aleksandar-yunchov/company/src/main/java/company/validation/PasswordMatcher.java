package company.validation;


import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatcher implements ConstraintValidator<Match, Object>
{
   private String firstFieldName;
   private String secondFieldName;
   private String message;

   @Override
   public void initialize(Match constraintAnnotation)
   {
      this.firstFieldName = constraintAnnotation.first();
      this.secondFieldName = constraintAnnotation.second();
      this.message = constraintAnnotation.message();
   }

   @Override
   public boolean isValid(Object passwordsRequest, ConstraintValidatorContext context)
   {
      BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(passwordsRequest);
      Object firstValue = wrapper.getPropertyValue(firstFieldName);
      Object secondValue = wrapper.getPropertyValue(secondFieldName);

      boolean valid = true;

      if (firstValue == null || secondValue == null) {
         valid = false;
      }
      else if (!firstValue.equals(secondValue)) {
         valid = false;
      }

      if (!valid) {
         context.buildConstraintViolationWithTemplate(message)
            .addConstraintViolation()
            .disableDefaultConstraintViolation();
      }

      return valid;
   }
}
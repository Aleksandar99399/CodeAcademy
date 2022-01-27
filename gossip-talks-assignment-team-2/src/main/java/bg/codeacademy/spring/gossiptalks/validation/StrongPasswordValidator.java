package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String>
{


   @Override
   public void initialize(StrongPassword constraintAnnotation)
   {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }

   @Override
   public boolean isValid(String password, ConstraintValidatorContext context)
   {
      if (password == null) {
         createConstraint(context, "Password cannot be null");
         return false;
      }
      else if (password.length() < 4 || password.length() > 20) {
         createConstraint(context, "Password must be between 4 and 14 symbols");
         return false;
      }
      else {
         String reg = "^[0-9\\S]+$";
         Pattern pattern = Pattern.compile(reg);
         Matcher matcher = pattern.matcher(password);

         if (!matcher.matches()) {
            createConstraint(context, "Invalid symbols in password");
            return false;
         }
      }
      return true;
   }

   private void createConstraint(ConstraintValidatorContext context, String s)
   {
      context.buildConstraintViolationWithTemplate(s)
         .addConstraintViolation()
         .disableDefaultConstraintViolation();
   }
}

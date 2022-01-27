package bg.startit.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, String>
{
   private String message;

   @Override
   public void initialize(StrongPassword constraintAnnotation)
   {
      this.message = constraintAnnotation.message();
   }

   //можем да премахнем валидацията за null и празен String
   // можем да преработим метода за да върнем повече от една грешки
   @Override
   public boolean isValid(String password, ConstraintValidatorContext context)
   {
//      if (password == null) {
//         this.message = "Паролата или Потвърждаването не може да бъде null | StrongPassword";
//         context.buildConstraintViolationWithTemplate(message)
//            .addConstraintViolation()
//            .disableDefaultConstraintViolation();
//////            //без реда долу работи
////            .addPropertyNode(password)
////            .addConstraintViolation()
////            .disableDefaultConstraintViolation();
//         return false;
//     }
      String reg = "^([\\w\\d]+)$";
      Pattern pattern = Pattern.compile(reg);
      Matcher matcher = pattern.matcher(password);
      return matcher.matches();
   }
}

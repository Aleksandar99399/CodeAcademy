package bg.codeacademy.spring.gossiptalks.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class HtmlTagsValidator implements ConstraintValidator<HtmlTags, String>
{


   @Override
   public void initialize(HtmlTags constraintAnnotation)
   {
      ConstraintValidator.super.initialize(constraintAnnotation);
   }

   @Override
   public boolean isValid(String text, ConstraintValidatorContext constraintValidatorContext)
   {
      Pattern htmlPattern = Pattern.compile("<(\\\"[^\\\"]*\\\"|'[^']*'|[^'\\\">])*>");

      if (htmlPattern.matcher(text).find()) {
         return false;
      }
      return true;
   }
}

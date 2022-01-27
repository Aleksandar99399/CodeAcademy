package bg.startit.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatcher.class)
public @interface Match
{
   String message() default "Passwords not match";

   Class<?>[] groups() default {};

   Class<? extends Payload>[] payload() default {};
}
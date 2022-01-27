package bg.codeacademy.spring.gossiptalks.model.dto.user;

public class CommonPasswordRequest
{
   private String password;
   private String passwordConfirmation;

   public CommonPasswordRequest()
   {
   }

   public String getPassword()
   {
      return password;
   }

   public CommonPasswordRequest setPassword(String password)
   {
      this.password = password;
      return this;
   }

   public String getPasswordConfirmation()
   {
      return passwordConfirmation;
   }

   public CommonPasswordRequest setPasswordConfirmation(String passwordConfirmation)
   {
      this.passwordConfirmation = passwordConfirmation;
      return this;
   }
}

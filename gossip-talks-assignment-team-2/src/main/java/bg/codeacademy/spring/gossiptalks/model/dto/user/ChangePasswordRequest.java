package bg.codeacademy.spring.gossiptalks.model.dto.user;

import bg.codeacademy.spring.gossiptalks.validation.Match;
import bg.codeacademy.spring.gossiptalks.validation.StrongPassword;
import bg.codeacademy.spring.gossiptalks.validation.group.SecondExecuteGroup;

@Match(first = "password", second = "passwordConfirmation", groups = SecondExecuteGroup.class)
public class ChangePasswordRequest
{
   private String password;
   private String passwordConfirmation;
   private String oldPassword;

   public ChangePasswordRequest()
   {
   }

   @StrongPassword
   public String getPassword()
   {
      return password;
   }

   public ChangePasswordRequest setPassword(String password)
   {
      this.password = password;
      return this;
   }

   public String getPasswordConfirmation()
   {
      return passwordConfirmation;
   }

   public ChangePasswordRequest setPasswordConfirmation(String passwordConfirmation)
   {
      this.passwordConfirmation = passwordConfirmation;
      return this;
   }

   public String getOldPassword()
   {
      return oldPassword;
   }

   public ChangePasswordRequest setOldPassword(String oldPassword)
   {
      this.oldPassword = oldPassword;
      return this;
   }
}

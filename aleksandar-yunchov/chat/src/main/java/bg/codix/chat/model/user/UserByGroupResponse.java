package bg.codix.chat.model.user;

public class UserByGroupResponse extends User
{

   private String group;

   public UserByGroupResponse()
   {
   }

   public String getGroup()
   {
      return group;
   }

   public UserByGroupResponse setGroup(String group)
   {
      this.group = group;
      return this;
   }
}

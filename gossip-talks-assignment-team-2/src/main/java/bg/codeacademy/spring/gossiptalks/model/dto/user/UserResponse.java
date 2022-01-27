package bg.codeacademy.spring.gossiptalks.model.dto.user;

public class UserResponse
{
   private String  email;
   private String  username;
   private Boolean following;
   private String  name;

   public UserResponse()
   {
   }

   public String getEmail()
   {
      return email;
   }

   public UserResponse setEmail(String email)
   {
      this.email = email;
      return this;
   }

   public String getUsername()
   {
      return username;
   }

   public UserResponse setUsername(String username)
   {
      this.username = username;
      return this;
   }

   public Boolean getFollowing()
   {
      return following;
   }

   public UserResponse setFollowing(Boolean following)
   {
      this.following = following;
      return this;
   }

   public String getName()
   {
      return name;
   }

   public UserResponse setName(String name)
   {
      this.name = name;
      return this;
   }


}

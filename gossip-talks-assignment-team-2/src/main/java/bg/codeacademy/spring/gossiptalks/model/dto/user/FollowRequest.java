package bg.codeacademy.spring.gossiptalks.model.dto.user;

public class FollowRequest
{
   private Boolean follow;

   public FollowRequest()
   {
   }

   public Boolean getFollow()
   {
      return follow;
   }

   public FollowRequest setFollow(Boolean follow)
   {
      this.follow = follow;
      return this;
   }
}

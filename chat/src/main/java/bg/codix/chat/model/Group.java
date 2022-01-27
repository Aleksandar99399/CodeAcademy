package bg.codix.chat.model;

public class Group
{
   private Long groupId;
   private String group;

   public Group()
   {
   }

   public Long getGroupId()
   {
      return groupId;
   }

   public Group setGroupId(Long groupId)
   {
      this.groupId = groupId;
      return this;
   }

   public String getGroup()
   {
      return group;
   }

   public Group setGroup(String group)
   {
      this.group = group;
      return this;
   }
}

package bg.codeacademy.spring.gossiptalks.model.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Entity
public class Gossip
{
   private String         id;
   private String         text;
   private String         username;
   private OffsetDateTime datetime;


   public Gossip()
   {

   }

   public Gossip(String id, String text, OffsetDateTime dateTime)
   {
      this.id = id;
      this.text = text;
      this.datetime = dateTime;
   }

   public Gossip(String id, String text, String username)
   {
      this.id = id;
      this.text = text;
      this.username = username;
   }

   @Id
   @Pattern(regexp = "[A-Z0-9]+")
   public String getId()
   {
      return id;
   }

   public Gossip setId(String id)
   {
      this.id = id;
      return this;
   }


   public String getText()
   {
      return text;
   }

   public Gossip setText(String text)
   {
      this.text = text;
      return this;
   }

   public String getUsername()
   {
      return username;
   }

   public Gossip setUsername(String username)
   {
      this.username = username;
      return this;
   }

   public OffsetDateTime getDatetime()
   {
      return datetime;
   }

   public void setDatetime(OffsetDateTime datetime)
   {
      this.datetime = datetime;
   }
}

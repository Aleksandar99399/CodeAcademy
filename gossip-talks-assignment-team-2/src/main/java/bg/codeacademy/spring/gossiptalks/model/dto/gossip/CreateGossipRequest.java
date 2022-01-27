package bg.codeacademy.spring.gossiptalks.model.dto.gossip;

import bg.codeacademy.spring.gossiptalks.validation.HtmlTags;

import javax.validation.constraints.NotBlank;

public class CreateGossipRequest
{
   private String text;

   public CreateGossipRequest()
   {
   }


   @HtmlTags
   @NotBlank
   public String getText()
   {
      return text;
   }

   public CreateGossipRequest setText(String text)
   {
      this.text = text;
      return this;
   }
}

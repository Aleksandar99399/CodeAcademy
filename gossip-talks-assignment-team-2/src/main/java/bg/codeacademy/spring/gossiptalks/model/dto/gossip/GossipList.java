package bg.codeacademy.spring.gossiptalks.model.dto.gossip;

import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;

import java.util.ArrayList;
import java.util.List;

public class GossipList
{
   private Integer      pageNumber;
   private Integer      pageSize;
   private Integer      count;
   private Integer      total;
   private List<Gossip> content = new ArrayList<>();

   public GossipList()
   {
   }

   public Integer getPageNumber()
   {
      return pageNumber;
   }

   public GossipList setPageNumber(Integer pageNumber)
   {
      this.pageNumber = pageNumber;
      return this;
   }

   public Integer getPageSize()
   {
      return pageSize;
   }

   public GossipList setPageSize(Integer pageSize)
   {
      this.pageSize = pageSize;
      return this;
   }

   public Integer getCount()
   {
      return count;
   }

   public GossipList setCount(Integer count)
   {
      this.count = count;
      return this;
   }

   public Integer getTotal()
   {
      return total;
   }

   public GossipList setTotal(Integer total)
   {
      this.total = total;
      return this;
   }

   public List<Gossip> getContent()
   {
      return content;
   }

   public GossipList setContent(List<Gossip> content)
   {
      this.content = content;
      return this;
   }
}

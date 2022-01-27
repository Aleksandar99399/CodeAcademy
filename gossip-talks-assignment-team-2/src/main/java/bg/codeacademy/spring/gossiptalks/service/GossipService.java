package bg.codeacademy.spring.gossiptalks.service;

import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface GossipService
{
   GossipList getAll(String username, Pageable pages);

   Gossip save(String text, String username);

   GossipList createGossipList(Page<Gossip> allByUsernameIn);

   GossipList getAllByUsernameOrderByDatetime(String username, PageRequest pageRequest);
}

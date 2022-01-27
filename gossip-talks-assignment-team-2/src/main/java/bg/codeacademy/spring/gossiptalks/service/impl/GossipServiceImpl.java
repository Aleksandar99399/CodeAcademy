package bg.codeacademy.spring.gossiptalks.service.impl;

import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.repository.GossipRepository;
import bg.codeacademy.spring.gossiptalks.service.GossipService;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class GossipServiceImpl implements GossipService
{
   private final GossipRepository gossipRepository;
   private final UserService      userService;

   public GossipServiceImpl(GossipRepository gossipRepository, UserService userService)
   {
      this.gossipRepository = gossipRepository;
      this.userService = userService;
   }

   @Override
   public GossipList getAll(String username, Pageable pages)
   {
      Set<User> usersFollowing = userService.getByUsername(username).getFriends();
      List<String> collect = usersFollowing.stream().map(User::getUsername).collect(Collectors.toList());

      Page<Gossip> allByUsernameIn = gossipRepository.findAllByUsernameInOrderByDatetimeDesc(collect, pages);
      return createGossipList(allByUsernameIn);
   }

   @Override
   public Gossip save(String text, String username)
   {
      OffsetDateTime offsetDateTime = getOffsetDateTime();

      Gossip gossip = new Gossip();
      gossip.setId(encodeText());
      gossip.setText(text);
      gossip.setUsername(username);
      gossip.setDatetime(offsetDateTime);
      Gossip save = gossipRepository.save(gossip);

      this.addGossipToUser(username, gossip);

      return save;
   }

   @Override
   public GossipList createGossipList(Page<Gossip> allByUsernameIn)
   {
      GossipList gossipList = new GossipList();
      gossipList.setContent(allByUsernameIn.getContent());
      gossipList.setCount(allByUsernameIn.getNumberOfElements());
      gossipList.setPageNumber(allByUsernameIn.getNumber());
      gossipList.setPageSize(allByUsernameIn.getSize());
      gossipList.setTotal((int) allByUsernameIn.getTotalElements());
      return gossipList;
   }

   @Override
   public GossipList getAllByUsernameOrderByDatetime(String username, PageRequest pageRequest)
   {
      User byUsername = userService.getByUsername(username);
      Page<Gossip> pagesForAllGossipsByUser =
         gossipRepository.findAllByUsernameOrderByDatetimeDesc(byUsername.getUsername(), pageRequest);
      return this.createGossipList(pagesForAllGossipsByUser);
   }

   private void addGossipToUser(String username, Gossip gossip)
   {
      User user = userService.getByUsername(username);
      user.getGossips().add(gossip);
      userService.saveUserInDB(user);
   }

   private static int id = 0;

   private String encodeText()
   {
      return Long.toString(++id, 36).toUpperCase();
   }

   private OffsetDateTime getOffsetDateTime()
   {
      LocalDateTime now = LocalDateTime.now();
      ZoneId zone = ZoneId.of("Europe/Sofia");
      ZoneOffset zoneOffSet = zone.getRules().getOffset(now);
      return OffsetDateTime.of(LocalDateTime.now(), zoneOffSet);
   }

}

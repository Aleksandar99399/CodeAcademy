package bg.codeacademy.spring.gossiptalks.controller;

import bg.codeacademy.spring.gossiptalks.model.dto.gossip.CreateGossipRequest;
import bg.codeacademy.spring.gossiptalks.model.dto.gossip.GossipList;
import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;
import bg.codeacademy.spring.gossiptalks.service.GossipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/gossips")
@Validated
public class GossipController
{

   private final GossipService gossipService;

   @Autowired
   public GossipController(GossipService gossipService)
   {
      this.gossipService = gossipService;
   }

   @GetMapping
   public ResponseEntity<GossipList> getAll(@Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNo,
                                            @Min(value = 1) @Max(100) @RequestParam(defaultValue = "20")
                                               Integer pageSize,
                                            Principal principal)
   {
      PageRequest pages = PageRequest.of(pageNo, pageSize);
      GossipList all = gossipService.getAll(principal.getName(), pages);
      return ResponseEntity.ok(all);
   }

   @RequestMapping(method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
   public ResponseEntity<Gossip> postGossip(@Valid CreateGossipRequest gossipRequest, BindingResult bindingResult,
                                            Principal principal)
   {
      Gossip gossip = gossipService.save(gossipRequest.getText(), principal.getName());
      return ResponseEntity.ok(gossip);
   }
}

package bg.codeacademy.spring.gossiptalks.repository;

import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GossipRepository extends JpaRepository<Gossip, Long>
{
   Page<Gossip> findAllByUsernameOrderByDatetimeDesc(String username, Pageable pageable);

   Page<Gossip> findAllByUsernameInOrderByDatetimeDesc(List<String> users, Pageable pageable);


}

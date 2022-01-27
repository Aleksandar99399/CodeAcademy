package bg.codeacademy.spring.gossiptalks.repository;

import bg.codeacademy.spring.gossiptalks.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
   Optional<User> findByUsername(String username);

   @Query("SELECT f FROM User as u JOIN u.friends as f WHERE u.id=:id "
      + "AND" + " (LOWER(f.name) LIKE LOWER(CONCAT('%', :subs,'%'))" +
      " OR LOWER(f.username) LIKE LOWER(CONCAT('%', :subs,'%')))"
   )
//   @Query("SELECT f FROM User as u UNION")
   List<User> findAllFriendsWithGivenSubstring(@Param("id") Long id
      , @Param("subs") String subs
   );

   @Query("SELECT u.friends FROM User AS u WHERE u.id=:id")
   List<User> findAllFriends(@Param("id") Long id);

   @Query("SELECT u FROM User AS u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :subs,'%'))" +
      " OR LOWER(u.username) LIKE LOWER(CONCAT('%', :sub" +
      "s,'%'))")
   List<User> findAllByNameOrUsernameContains(@Param("subs") String nameOrUsername);
}

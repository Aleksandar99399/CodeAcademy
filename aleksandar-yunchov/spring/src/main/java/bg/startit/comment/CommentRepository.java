package bg.startit.comment;


import bg.startit.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment,Long>
{
  List<Comment> findByUser(User user);

  Page<Comment> findAllByBook_Id(Pageable pageable, Long bookId);
}

package bg.startit.comment;


import bg.startit.comment.dto.RequestComment;
import bg.startit.comment.dto.ResponseComment;
import bg.startit.user.User;
import bg.startit.user.dto.ResponseUser;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService
{

   Comment getById(Long id);

   List<ResponseComment> getAll(Pageable pageable);

   ResponseComment insert(RequestComment comment);

   void delete(Long id);

   List<Comment> getByUser(User user);

   List<ResponseComment> getAllCommentsByBook(Pageable pageable, Long id);

   ResponseUser getUserFromComment(List<Comment> allCommentsByBook, Long commentId);
}

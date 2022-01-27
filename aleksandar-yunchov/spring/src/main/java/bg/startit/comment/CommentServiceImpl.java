package bg.startit.comment;


import bg.startit.book.Book;
import bg.startit.book.BookService;
import bg.startit.comment.dto.RequestComment;
import bg.startit.comment.dto.ResponseComment;
import bg.startit.exception.global.NotFoundException;
import bg.startit.user.User;
import bg.startit.user.UserService;
import bg.startit.user.dto.ResponseUser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService
{

   @Autowired
   private       ModelMapper       modelMapper;
   private final CommentRepository repository;
   private final UserService       userService;
   private final BookService       bookService;

   @Autowired
   public CommentServiceImpl(CommentRepository repository, UserService userService, BookService bookService)
   {
      this.repository = repository;
      this.userService = userService;
      this.bookService = bookService;
   }

   @Override
   public Comment getById(Long id)
   {
      return repository.findById(id).get();
   }

   @Override
   public List<ResponseComment> getAll(Pageable pageable)
   {
      return repository.findAll(pageable).stream().map(c -> modelMapper.map(c, ResponseComment.class)).collect(Collectors.toList());
   }

   @Override
   public ResponseComment insert(RequestComment comment)
   {

      Comment map = new Comment();
      map.setText(comment.getText());
      Book book = bookService.getBookById(comment.getBook().getId());
      map.setBook(book);
      User user = userService.getUserById(comment.getUser().getId());
      map.setUser(user);

      Comment save = repository.save(map);
      return this.modelMapper.map(save, ResponseComment.class);
   }

   @Override

   public void delete(Long id)
   {
      repository.deleteById(id);
   }

   @Override
   public List<Comment> getByUser(User user)
   {
      return repository.findByUser(user);
   }

   @Override
   public List<ResponseComment> getAllCommentsByBook(Pageable pageable, Long bookId)
   {
      return repository.findAllByBook_Id(pageable,bookId)
         .stream().map(c -> modelMapper.map(c, ResponseComment.class))
         .collect(Collectors.toList());
   }

   @Override
   public ResponseUser getUserFromComment(List<Comment> allCommentsByBook, Long commentId)
   {
      Comment saved = null;
      for (Comment comment : allCommentsByBook) {
         if (comment.getId().equals(commentId)) {
            saved = comment;
            break;
         }
      }
      if (saved == null) {
         throw new NotFoundException("Не съществува коментар с ID: " + commentId);
      }
      User user = saved.getUser();
      return this.modelMapper.map(user, ResponseUser.class);
   }
}

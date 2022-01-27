package bg.startit.comment;

import bg.startit.comment.dto.RequestComment;
import bg.startit.comment.dto.ResponseComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/comments")
public class CommentController
{

   private final CommentService commentService;

   @Autowired
   public CommentController(CommentService commentService)
   {
      this.commentService = commentService;
   }

   @GetMapping
   public ResponseEntity<List<ResponseComment>> getAll(@Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNumber,
                                                       @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                          Integer pageCapacity)
   {
      List<ResponseComment> all = commentService.getAll(PageRequest.of(pageNumber, pageCapacity));
      return new ResponseEntity<>(all, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<Comment> getById(@PathVariable("id") Long id)
   {
      Comment comment = commentService.getById(id);
      return new ResponseEntity<>(comment, HttpStatus.OK);
   }

   @PostMapping()
   public ResponseEntity<ResponseComment> createComment(@RequestBody RequestComment comment)
   {
      ResponseComment insert = commentService.insert(comment);
      return new ResponseEntity<>(insert, HttpStatus.CREATED);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable("id") Long id)
   {
      commentService.delete(id);

   }

   @PutMapping("/{id}")
   public ResponseEntity<ResponseComment> update(@PathVariable("id") Long id, @RequestBody RequestComment comment)
   {
      ResponseComment insert = commentService.insert(comment);
      return new ResponseEntity<>(insert, HttpStatus.OK);
   }

   @GetMapping("/{id}/user")
   public ResponseEntity<Void> getTheUser(@PathVariable Long id)
   {
      URI location = ServletUriComponentsBuilder
         .fromCurrentContextPath()
         .path("/api/v1/users/{id}")
         .buildAndExpand(commentService.getById(id).getUser().getId())
         .toUri();
      return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
   }

   @GetMapping("all_comments_by_book/{book_id}")
   public ResponseEntity<List<ResponseComment>> getAllCommentsByBook(
      @PathVariable("book_id") Long id,
      @Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNumber,
      @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
         Integer pageCapacity
   )
   {
      List<ResponseComment> allCommentsByBook =
         commentService.getAllCommentsByBook(PageRequest.of(pageNumber,pageCapacity),id);
      return new ResponseEntity<>(allCommentsByBook, HttpStatus.OK);
   }

}

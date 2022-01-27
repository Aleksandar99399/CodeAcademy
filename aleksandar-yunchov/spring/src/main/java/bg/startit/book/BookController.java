package bg.startit.book;


import bg.startit.book.dto.CreateBookDto;
import bg.startit.book.dto.ResponseBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
//primitive types
@Validated
@Transactional
@RequestMapping("/api/v1/books")
public class BookController
{
   private final BookService bookService;

   @Autowired
   public BookController(BookService bookService)
   {
      this.bookService = bookService;
   }

//      @GetMapping("/{book_id}/comments/{comment_id}/user")
//   public ResponseEntity<ResponseUser> getBookComments(@PathVariable("book_id") Long bookId,
//                                                       @PathVariable("comment_id") Long commentId)
//   {
//      List<Comment> allCommentsByBook = commentService.getAllCommentsByBook(bookId);
//      ResponseUser userFromComment = commentService.getUserFromComment(allCommentsByBook, commentId);
//      return new ResponseEntity<>(userFromComment, HttpStatus.OK);
//   }

//   @GetMapping("/{book_id}/comments/{comment_id}")
//   public ResponseEntity<Void> getBookComments(@PathVariable("book_id") Long bookId,
//                                               @PathVariable("comment_id") Long commentId)
//   {
//
//      URI location = ServletUriComponentsBuilder
//         .fromCurrentContextPath()
//         .path("/api/v1/comments/{id}/user")
//         .buildAndExpand(
//            bookService.getBookById(bookId).getComments()
//               .stream().filter(c -> c.getId().equals(commentId))
//               .findFirst().get())
//         .toUri();
//      return ResponseEntity.status(HttpStatus.FOUND).location(location).build();
//   }

   enum FilterType
   {
      YEAR_EQ,
      YEAR_AFTER
   }

   // /api/v1/books?filter=year>1900,author~Ivan

   @GetMapping()
   public ResponseEntity<List<ResponseBook>> getAll(@Min(value = 0) @RequestParam(defaultValue = "0") Integer pageNumber,
                                                    @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                       Integer pageCapacity,
                                                    @RequestParam("filter") FilterType filter,
                                                    @RequestParam("year") Integer year)
   {
      if (filter != null && year == null) {
         return ResponseEntity.badRequest().build();
      }
      List<ResponseBook> result;
      PageRequest page = PageRequest.of(pageNumber, pageCapacity);
      if (filter == FilterType.YEAR_AFTER) {
         result = bookService.getByYearGreaterThan(page, year);
      }
      else if (filter == FilterType.YEAR_EQ) {
         result = bookService.findAllByYear(year, page);
      }
      else {
         result = bookService.getAll(page);
      }

      return new ResponseEntity<>(result, HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<ResponseBook> getById(@Min(value = 1, message = "Не съществува потребител с ID 0 или по-малко")
                                               @PathVariable("id") Long id)
   {
      ResponseBook book = bookService.getById(id);
      return new ResponseEntity<>(book, HttpStatus.OK);
   }

   @PostMapping
   public ResponseEntity<ResponseBook> createBook(@Valid @RequestBody CreateBookDto book)
   {
      // Трябва да предаваме параметри

      ResponseBook insert = bookService.create(book);
      return new ResponseEntity<>(insert, HttpStatus.CREATED);
   }

   @DeleteMapping("/{id}")
   public void delete(@PathVariable("id") Long id)
   {
      bookService.delete(id);
   }

   @PutMapping("/{id}")
   public ResponseEntity<ResponseBook> update(@PathVariable("id") Long id, @Valid @RequestBody CreateBookDto book)
   {
      ResponseBook insert = bookService.create(book);
      return new ResponseEntity<>(insert, HttpStatus.OK);
   }
}

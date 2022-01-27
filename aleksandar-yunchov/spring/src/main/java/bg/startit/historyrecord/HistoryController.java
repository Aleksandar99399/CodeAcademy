package bg.startit.historyrecord;


import bg.startit.book.dto.ResponseBook;
import bg.startit.exception.global.NotFoundException;
import bg.startit.historyrecord.dto.CreateHistoryDto;
import bg.startit.historyrecord.dto.ResponseHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.security.Principal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/history")
public class HistoryController
{
   private final HistoryService historyService;

   @Autowired
   public HistoryController(HistoryService historyService)
   {
      this.historyService = historyService;
   }

   @GetMapping
   public ResponseEntity<List<ResponseHistory>> getAllHistory(@Min(value = 0) @RequestParam(defaultValue = "0")
                                                                 Integer pageNumber,
                                                              @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                                 Integer pageCapacity)
   {
      List<ResponseHistory> allHistory = this.historyService.getAllHistory(PageRequest.of(pageNumber, pageCapacity));
      return new ResponseEntity<>(allHistory, HttpStatus.OK);
   }


   @PostMapping
   public ResponseEntity<ResponseHistory> lendBookByUser(@RequestBody CreateHistoryDto createHistoryDto)
   {
      ResponseHistory responseHistory = this.historyService.lendBookByUser(createHistoryDto.getUser(), createHistoryDto.getBook());
      return new ResponseEntity<>(responseHistory, HttpStatus.OK);
   }

   @PutMapping("return_book/{history_id}")
   public ResponseEntity<ResponseHistory> returnedBookByUser(@PathVariable("history_id") Long id)
   {
      ResponseHistory responseHistory = historyService.returnedBookByUser(id);
      return new ResponseEntity<>(responseHistory, HttpStatus.OK);
   }

   @GetMapping("/current_user_books")
   public ResponseEntity<List<ResponseHistory>> getAllBooksInCurrentUser(Principal principal,
                                                                         @Min(value = 0)
                                                                         @RequestParam(defaultValue = "0")
                                                                            Integer pageNumber,
                                                                         @Min(value = 1) @Max(100)
                                                                         @RequestParam(defaultValue = "100")
                                                                            Integer pageCapacity)
   {
      List<ResponseHistory> allBookForUser =
         historyService.getAllHistoryForUser(principal.getName(), PageRequest.of(pageNumber, pageCapacity));
      if (allBookForUser.isEmpty()) {
         throw new NotFoundException("Няма същесвуващи записи за вас");
      }
      return new ResponseEntity<>(allBookForUser, HttpStatus.OK);
   }

   @GetMapping("all_users_by_book/{book_id}")
   public ResponseEntity<List<HistoryRecord>> getAllUsersByBook(@PathVariable("book_id") Long id,
                                                                @Min(value = 0) @RequestParam(defaultValue = "0")
                                                                   Integer pageNumber,
                                                                @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                                   Integer pageCapacity)
   {
      List<HistoryRecord> allUsersHoldBook = historyService.getAllUsersHoldBook(id, PageRequest.of(pageNumber, pageCapacity));
      return new ResponseEntity<>(allUsersHoldBook, HttpStatus.OK);
   }

   @GetMapping("all_books_by_user/{user_id}")
   public ResponseEntity<List<ResponseBook>> getAllBooksByUser(@PathVariable("user_id") Long id,
                                                               @Min(value = 0) @RequestParam(defaultValue = "0")
                                                                  Integer pageNumber,
                                                               @Min(value = 1) @Max(100) @RequestParam(defaultValue = "100")
                                                                  Integer pageCapacity)
   {
      List<ResponseBook> allBookHoldUser = historyService.getAllBookHoldUser(id, PageRequest.of(pageNumber, pageCapacity));
      return new ResponseEntity<>(allBookHoldUser, HttpStatus.OK);
   }

}

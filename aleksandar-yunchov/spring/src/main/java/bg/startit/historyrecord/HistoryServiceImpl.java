package bg.startit.historyrecord;


import bg.startit.book.Book;
import bg.startit.book.BookService;
import bg.startit.book.dto.ResponseBook;
import bg.startit.exception.global.NotFoundException;
import bg.startit.historyrecord.dto.CreateHistoryDto;
import bg.startit.historyrecord.dto.ResponseHistory;
import bg.startit.user.User;
import bg.startit.user.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoryServiceImpl implements HistoryService
{

   private final HistoryRepository repository;
   private final UserService       userService;
   private final BookService       bookService;
   private final ModelMapper       modelMapper;

   @Autowired
   public HistoryServiceImpl(HistoryRepository repository, UserService userService, BookService bookService, ModelMapper modelMapper)
   {
      this.repository = repository;
      this.userService = userService;
      this.bookService = bookService;
      this.modelMapper = modelMapper;
   }

   @Override
   public ResponseHistory lendBookByUser(CreateHistoryDto createHistoryDto)
   {
      HistoryRecord historyRecord = new HistoryRecord();
      historyRecord.setDateOfLending(getCurrentDate());
      historyRecord.setBook(bookService.getBookById(createHistoryDto.getBook()));
      historyRecord.setUser(userService.getUserById(createHistoryDto.getUser()));

      repository.save(historyRecord);
      return this.modelMapper.map(historyRecord, ResponseHistory.class);
   }

   @Override
   public List<ResponseHistory> getAllHistory(Pageable pageable)
   {
      return repository.findAll(pageable).stream()
         .map(r -> this.modelMapper.map(r, ResponseHistory.class))
         .collect(Collectors.toList());
   }

   @Override
   public List<ResponseHistory> getAllHistoryForUser(String email,Pageable pageable)
   {
      User userByEmail = userService.getUserByEmail(email);
      return repository.findAllByUser(userByEmail,pageable).stream()
         .map(h -> this.modelMapper.map(h, ResponseHistory.class)).collect(Collectors.toList());
   }

   @Override
   public ResponseHistory lendBookByUser(Long user, Long book)
   {
      User userById = this.userService.getUserById(user);
      Book bookById = this.bookService.getBookById(book);
      bookService.decreaseStockByBook(bookById);

      HistoryRecord historyRecord = new HistoryRecord();
      historyRecord.setUser(userById);
      historyRecord.setBook(bookById);
      historyRecord.setDateOfLending(getCurrentDate());

      repository.save(historyRecord);
      return this.modelMapper.map(historyRecord, ResponseHistory.class);
   }

   @Override
   public ResponseHistory returnedBookByUser(Long id)
   {
      if (!repository.findById(id).isPresent()) {
         throw new NotFoundException("Не същестува запис с това ID: " + id);
      }
      HistoryRecord historyRecord = repository.findById(id).get();
      historyRecord.setDateOfReturn(getCurrentDate());
      historyRecord = repository.save(historyRecord);
      return this.modelMapper.map(historyRecord, ResponseHistory.class);
   }

   @Override
   public List<HistoryRecord> getAllUsersHoldBook(Long id, Pageable pageable)
   {
      Book bookById = bookService.getBookById(id);
      List<HistoryRecord> collect = repository.findAllByBook(bookById, pageable);

      return collect;
   }

   @Override
   public HistoryRecord getHistoryById(Long id)
   {
      return repository.findById(id).get();
   }

   @Override
   public List<ResponseBook> getAllBookHoldUser(Long id, Pageable pageable)
   {
      User userById = userService.getUserById(id);
      List<ResponseBook> collect = repository.findAllByUser(userById,pageable)
         .stream().map(HistoryRecord::getBook).map(b -> modelMapper.map(b, ResponseBook.class))
         .collect(Collectors.toList());
      return collect;
   }

   private Date getCurrentDate()
   {
      return new Date();
   }

}

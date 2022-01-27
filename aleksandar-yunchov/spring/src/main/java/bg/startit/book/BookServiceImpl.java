package bg.startit.book;


import bg.startit.exception.bookexceptions.NotEnoughStockException;
import bg.startit.exception.global.NotFoundException;
import bg.startit.book.dto.CreateBookDto;
import bg.startit.book.dto.ResponseBook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService
{
   private final BookRepository repository;
   private final ModelMapper    modelMapper;

   @Autowired
   public BookServiceImpl(BookRepository repository, ModelMapper modelMapper)
   {
      this.repository = repository;
      this.modelMapper = modelMapper;
   }

   @Override
   public ResponseBook getById(Long id)
   {
      if (checkBookExist(id)) {
         return this.modelMapper.map(repository.findById(id).get(), ResponseBook.class);
      }
      throw new NotFoundException("Не е намерена книга с ID: " + id);
   }

   @Override
   public Book getBookById(Long id)
   {
      if (checkBookExist(id)) {
         return repository.findById(id).get();
      }
      throw new NotFoundException("Не е намерена книга с ID: " + id);
   }

   @Override
   public List<ResponseBook> getAll(Pageable pageable)
   {
      if (repository.count() > 0) {
         Page<Book> books = repository.findAll(pageable);
         List<ResponseBook> collect = books.stream()
            .map(u -> this.modelMapper.map(u, ResponseBook.class)).collect(Collectors.toList());
         return collect;
      }
      else {
         throw new NotFoundException("Не са намерени съществуващи книги");
      }
   }

   @Override
   public ResponseBook create(CreateBookDto createBookDto)
   {
      Book book = this.modelMapper.map(createBookDto, Book.class);
      repository.save(book);
      return this.modelMapper.map(book, ResponseBook.class);
   }

   @Override
   public void delete(Long id)
   {
      repository.findById(id).orElseThrow(() -> new NotFoundException("Не е намерена книга с ID: " + id));
      repository.deleteById(id);
   }

   @Override
   public List<ResponseBook> getByYearGreaterThan(Pageable pageable, Integer year)
   {
      Page<Book> books = repository.findByYearGreaterThan(pageable, year);
      List<ResponseBook> collect = books.stream()
         .map(u -> this.modelMapper.map(u, ResponseBook.class)).collect(Collectors.toList());
      return collect;
   }

   private boolean checkBookExist(Long id)
   {
      return repository.findById(id).isPresent();
   }

   @Override
   public void decreaseStockByBook(Book book)
   {
      if (book.getStock() == 0) {
         throw new NotEnoughStockException("Няма налични бройки за тази книга");
      }
      book.setStock(book.getStock() - 1);
      repository.save(book);
   }

   @Override
   public List<ResponseBook> findAllByYear(Integer year, Pageable pageable)
   {
      List<ResponseBook> allByYear = repository.findAllByYear(year, pageable)
         .stream().map(b -> modelMapper.map(b, ResponseBook.class))
         .collect(Collectors.toList());
      return allByYear;
   }
}

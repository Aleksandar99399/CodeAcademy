package bg.startit.book;

import bg.startit.book.dto.CreateBookDto;
import bg.startit.book.dto.ResponseBook;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService
{
   ResponseBook getById(Long id);

   Book getBookById(Long id);

   //   List<Book> getAll();
   List<ResponseBook> getAll(Pageable pageable);

   ResponseBook create(CreateBookDto createBookDto);

   //   Book update(Book book);
   void delete(Long id);

   List<ResponseBook> getByYearGreaterThan(Pageable pageable, Integer year);

   void decreaseStockByBook(Book book);

   List<ResponseBook> findAllByYear(Integer year, Pageable pageable);
}

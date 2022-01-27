package bg.startit.historyrecord;


import bg.startit.book.Book;
import bg.startit.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface HistoryRepository extends JpaRepository<HistoryRecord, Long>
{
   List<HistoryRecord> findByUser(User user);

   List<HistoryRecord> findAllByUser(User user, Pageable pageable);

   List<HistoryRecord> findAllByBook(Book book, Pageable pageable);
}

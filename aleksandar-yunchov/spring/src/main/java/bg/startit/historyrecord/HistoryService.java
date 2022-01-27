package bg.startit.historyrecord;


import bg.startit.book.dto.ResponseBook;
import bg.startit.historyrecord.dto.CreateHistoryDto;
import bg.startit.historyrecord.dto.ResponseHistory;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryService
{
   ResponseHistory lendBookByUser(CreateHistoryDto createHistoryDto);

   List<ResponseHistory> getAllHistory(Pageable pageable);

   List<ResponseHistory> getAllHistoryForUser(String email,Pageable pageable);

   ResponseHistory lendBookByUser(Long user, Long book);

   ResponseHistory returnedBookByUser(Long id);

   List<HistoryRecord> getAllUsersHoldBook(Long id, Pageable pageable);

   HistoryRecord getHistoryById(Long id);

   List<ResponseBook> getAllBookHoldUser(Long id,Pageable pageable);
}

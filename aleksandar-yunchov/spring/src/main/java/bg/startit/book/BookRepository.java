package bg.startit.book;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BookRepository extends JpaRepository<Book, Long>
{
   Page<Book> findByYearGreaterThan(Pageable pageable, Integer year);

   List<Book> findAllByYear(Integer year, Pageable pageable);

}


package bg.startit.spring.first.project.books;

import bg.startit.book.Book;
import bg.startit.book.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.*;

import java.math.BigDecimal;

import static org.testng.Assert.*;

@DataJpaTest
@ContextConfiguration(locations = {"classpath:spring-test-config.xml"})
@Transactional
public class BookRepositoryTest extends AbstractTransactionalTestNGSpringContextTests
{
  @Autowired
  private BookRepository bookRepository;

  @BeforeClass
  public void beforeClass()
  {
    System.out.println("once on testing start");
  }

  @AfterClass
  public void afterClass()
  {
    System.out.println("once on testing end");
  }

  @BeforeMethod
  public void setUp()
  {
  }

  @AfterMethod
  public void tearDown()
  {
    System.out.println("tear down method started");
  }

  @BeforeSuite
  @Override
  protected void springTestContextPrepareTestInstance() throws Exception
  {
    super.springTestContextPrepareTestInstance();
  }

  @Test
  public void saveTest()
  {
    Book book = new Book();
    System.out.println("setup started");
    book.setAuthor("Author");
    book.setTitle("Some book");
    book.setIsbn("42473878474543");
    book.setPrice(BigDecimal.TEN);
    book.setStock(10);
    book.setYear(1950);
    assertNull(book.getId());
    bookRepository.save(book);
    assertNotNull(book.getId());

    assertTrue(book.getId() > 0, "Book id should be a positive number");
    assertEquals(book.getId(), Long.valueOf(100));
  }

  @Test
  public void deleteTest()
  {
    assertNotNull(bookRepository.findById(1L));
    bookRepository.deleteById(1L);
    assertNull(bookRepository.findById(1L).orElse(null));
  }

  @Test
  public void findAllTest()
  {
    assertNotNull(bookRepository.findAll());
    assertEquals(bookRepository.findAll(PageRequest.of(0, 3)).toList().size(), 3);
  }

//  @Test
//  public void findByIsbnTest()
//  {
//    assertEquals(bookRepository.findByIsbn("9788726329964").getIsbn(), "9788726329964");
//  }
//
//  @Test
//  public void findByAuthorTest()
//  {
//    assertTrue(bookRepository.findByAuthorIgnoreCase("иван вазов").stream().allMatch(e -> e.getAuthor().equalsIgnoreCase("иван вазов")));
//    assertTrue(bookRepository.findByAuthorIgnoreCase("Иван Вазов").stream().allMatch(e -> e.getAuthor().equalsIgnoreCase("иван вазов")));
//  }
//
//  @Test
//  public void findByTitleTest()
//  {
//    assertTrue(bookRepository.findByTitleIgnoreCase("под игото").stream().allMatch(e -> e.getTitle().equalsIgnoreCase("Под Игото")));
//    assertTrue(bookRepository.findByTitleIgnoreCase("Под Игото").stream().allMatch(e -> e.getTitle().equalsIgnoreCase("под игото")));
//  }


}
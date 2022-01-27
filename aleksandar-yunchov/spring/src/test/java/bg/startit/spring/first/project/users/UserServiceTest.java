//package bg.startit.spring.first.project.users;
//
//import bg.startit.role.RoleService;
//import bg.startit.user.User;
//import bg.startit.user.UserRepository;
//import bg.startit.user.UserServiceImpl;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.awt.print.Pageable;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Optional;
//
//import static org.testng.Assert.assertEquals;
//
//
//public class UserServiceTest extends AbstractTestNGSpringContextTests
//{
//  private final Long           USER_ID = 2L;
//  @Mock
//  private       UserRepository userRepository;
//  @Mock
//  RoleService roleService;
//
//  private Map<Long, User> fakeDb = new HashMap<>();
//  private UserServiceImpl userService;
//  private Pageable        pageable;
//
//  @BeforeClass
//  public void beforeClass(){
//    MockitoAnnotations.openMocks(this);
//    fakeDb.put(1L, new User("Nikolay","Megdanov","shcxatter2@gmail.com","password"));
//    fakeDb.put(2L, new User("Ilia","Georgiev","shcxatter2@abv.bg","password"));
//    fakeDb.put(3L, new User("Kostadin","Koikov","asdg@gmail.com","password"));
//  }
//
//  @BeforeMethod
//  public void setUp(){
//    userService = new UserServiceImpl(userRepository,roleService);
//  }
//
//  @Test
//  public void  getById_ReturnUser_IfIdIsValid(){
//    User expectedUser = fakeDb.get(USER_ID);
//
//    Mockito.when(userRepository.findById(Mockito.anyLong()))
//        .thenReturn(Optional.of(fakeDb.get(USER_ID)));
//
//    User actualUser = userService.findById(USER_ID);
//
//    Mockito.verify(userRepository, Mockito.atLeast(1)).findById(USER_ID);
//  }
//
//  @Test
//  public void getAll_ReturnsPageOfUsers_IfPageableIsCorrect(){
//
//    Mockito.when(userRepository.findAll(Mockito.any(org.springframework.data.domain.Pageable.class)))
//        .thenReturn(new PageImpl<User>(Arrays.asList(fakeDb.get(0L),fakeDb.get(2L))));
//
//    Page<User> expectedPage = userService.getAll(PageRequest.of(0,10));
//
//    assertEquals(expectedPage.getTotalElements(),2L);
//    Mockito.verify(userRepository, Mockito.atLeast(1)).findAll(Mockito.any(org.springframework.data.domain.Pageable.class));
//  }
//
//  @Test
//  public void deleteById_DeletesUser_IfIdIsCorrect(){
//    Mockito.doNothing()
//        .when(userRepository).deleteById(USER_ID);
//    userService.deleteById(USER_ID);
//    Mockito.verify(userRepository, Mockito.atLeast(1)).deleteById(Mockito.anyLong());
//  }
//}
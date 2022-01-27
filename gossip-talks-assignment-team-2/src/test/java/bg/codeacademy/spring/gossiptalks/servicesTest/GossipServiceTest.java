package bg.codeacademy.spring.gossiptalks.servicesTest;

import bg.codeacademy.spring.gossiptalks.model.entity.Gossip;
import bg.codeacademy.spring.gossiptalks.model.entity.User;
import bg.codeacademy.spring.gossiptalks.repository.GossipRepository;
import bg.codeacademy.spring.gossiptalks.repository.UserRepository;
import bg.codeacademy.spring.gossiptalks.service.GossipService;
import bg.codeacademy.spring.gossiptalks.service.UserService;
import bg.codeacademy.spring.gossiptalks.service.impl.GossipServiceImpl;
import org.testng.annotations.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.*;

@TestExecutionListeners(listeners = MockitoTestExecutionListener.class)
@ExtendWith(MockitoExtension.class)
public class GossipServiceTest
{

  @Mock
  private GossipRepository gossipRepository;
  @Mock
  private UserRepository   userRepository;


  @Mock
  private UserService   userService;
  private GossipService gossipService;

  private Gossip gossip;
  private User   user;

  @BeforeMethod
  public void setUp()
  {
    MockitoAnnotations.initMocks(this);
    this.user = new User(1L, "admin", "admin", "admin@gmail.com", "Admin Admin");
    this.gossip = new Gossip("asdqas", "AdminTweet", user.getUsername());
    gossipService = new GossipServiceImpl(gossipRepository, userService);
  }


  @Test
  public void try_to_save_gossip_with_correct_username_save()
  {
    Mockito.when(gossipRepository.save(Mockito.any())).thenReturn(gossip);

    Mockito.when(userService.getByUsername("admin")).thenReturn(user);

    Gossip gossipToReturn = gossipService.save(gossip.getText(), "admin");
    assertNotNull(gossipToReturn);
  }


}

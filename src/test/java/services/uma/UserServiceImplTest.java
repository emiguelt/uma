package services.uma;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import models.uma.User;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import repository.uma.UserRepository;
import services.uma.impl.UserServiceImpl;

@RunWith(org.mockito.runners.MockitoJUnitRunner.class)
public class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testGetUserById() {
        userService.getUserById(1);
        verify(userRepository).getById(1L);
    }
    
    @Test
    public void testGetUserByUsername() {
        userService.getUserByUsernameOrEmail("anUser");
        verify(userRepository).getByUsername("anUser");
    }

    @Test
    public void testGetUserList() {
       userService.getUserList(0, null);
       verify(userRepository).getList(0, 10);
    }

    @Test
    public void testSaveUser() {
        userService.saveUser(new User());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void testValidAuthentication() {
        when(userRepository.getByUsername("anUser")).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = new User();
                user.setId(1L).setUsername("anUser").setPassword("123");
                return user;
            }
        });
        
        assertNotNull(userService.authenticate("anUser", "123"));
        verify(userRepository).getByUsername("anUser");
    }
    

    @Test
    public void testInvalidAuthentication() {
        when(userRepository.getByUsername("anUser")).then(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) throws Throwable {
                User user = new User();
                user.setId(1L).setUsername("anUser").setPassword("123");
                return user;
            }
        });
        
        assertNull(userService.authenticate("anUser", "12"));
        assertNull(userService.authenticate("oterUser", "12"));
        verify(userRepository).getByUsername("anUser");
    }    

}
package Test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthManagerTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private HashLibrary hashLibrary;
    @InjectMocks
    private AuthManager authManager;

    private User testUser;
    private User mockUser;
    private final String TEST_EMAIL = "iit@du.ac";
    private final String TEST_PASSWORD = "123";
    private final String HASHED_PASSWORD = "12345";

    @Before
    public void setUp() {
        User mockUser = mock(User.class);

        when(mockUser.getHashedPassword()).thenReturn(HASHED_PASSWORD);
        when(userRepository.findByEmail(TEST_EMAIL)).thenReturn(mockUser);
        when(hashLibrary.hash(TEST_PASSWORD)).thenReturn(HASHED_PASSWORD);

        when(userRepository.findByEmail("not@du.ac")).thenReturn(null);
        when(hashLibrary.hash("wrongpass")).thenReturn("wronghash");

    }

    @Test
    public void testLoginSuccessValidCredentials() {
        assertTrue(authManager.login(TEST_EMAIL, TEST_PASSWORD));
        verify(userRepository).findByEmail(TEST_EMAIL);
        verify(hashLibrary).hash(TEST_PASSWORD);
    }

    @Test(expected = RuntimeException.class)
    public void testLoginExceptionUserNotFound() {
        assertFalse(authManager.login("not@du.ac", TEST_PASSWORD));
        verify(userRepository).findByEmail("not@du.ac");
        verify(hashLibrary, never()).hash(anyString());
    }

    @Test
    public void testLoginFailsPasswordIncorrect() {

        assertFalse(authManager.login(TEST_EMAIL, "wrongpass"));
        verify(userRepository).findByEmail(TEST_EMAIL);
        verify(hashLibrary).hash("wrongpass");
    }
}
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthManagerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private HashLibrary hashLibrary;

    @InjectMocks
    private AuthManager authManager;

    private User testUser;
    private final String testEmail = "test@example.com";
    private final String testPassword = "securePassword123";
    private final String hashedPassword = "hashedSecurePassword123";

    @BeforeEach
    void setUp() {
        // Create a test user
        testUser = new User();
        testUser.setUsername("testUser");
        testUser.setPassword(hashedPassword);

        // Define common mock behavior that can be reused across tests
        when(userRepository.findByEmail(testEmail)).thenReturn(testUser);
        when(hashLibrary.hash(testPassword)).thenReturn(hashedPassword);
    }

    @Test
    void login_Successful_WhenCredentialsMatch() {
        // Act
        boolean result = authManager.login(testEmail, testPassword);

        // Assert
        assertTrue(result);
        verify(userRepository).findByEmail(testEmail);
        verify(hashLibrary).hash(testPassword);
    }

    @Test
    void login_Fails_WhenUserNotFound() {
        // Arrange - override the default mock behavior for this specific test
        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(null);

        // Act
        boolean result = authManager.login("nonexistent@example.com", testPassword);

        // Assert
        assertFalse(result);
        verify(userRepository).findByEmail("nonexistent@example.com");
        verify(hashLibrary, never()).hash(anyString());
    }

    @Test
    void login_Fails_WhenPasswordDoesNotMatch() {
        // Arrange - override hashLibrary behavior for this test
        when(hashLibrary.hash("wrongPassword")).thenReturn("wrongHashedPassword");

        // Act
        boolean result = authManager.login(testEmail, "wrongPassword");

        // Assert
        assertFalse(result);
        verify(userRepository).findByEmail(testEmail);
        verify(hashLibrary).hash("wrongPassword");
    }

    @Test
    void login_Fails_WhenHashingFails() {
        // Arrange - simulate hashing failure
        when(hashLibrary.hash(testPassword)).thenThrow(new RuntimeException("Hashing error"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            authManager.login(testEmail, testPassword);
        });
        verify(userRepository).findByEmail(testEmail);
        verify(hashLibrary).hash(testPassword);
    }
}
package tests;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.*;
import org.openqa.selenium.Dimension;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AllTestCase {
    private WebDriver driver;
    private LoginPage loginPage;
    private BoardPage boardPage;
    private ListPage listPage;
    private CardPage cardPage;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(914, 693));
        loginPage = new LoginPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }


    @Test
    public void test1_LoginWithInvalidCredentials() {
        driver.get("http://localhost:4000/sign_in");
        loginPage.login("a@b", "wrongpassword");
        // Add assertion (e.g., check error message)
    }

    // **Test Case 2: Successful login**
    @Test
    public void test2_SuccessfulLogin() {
        driver.get("http://localhost:4000/sign_in");
        loginPage.login("c@d", "12345");
        // Add assertion (e.g., verify dashboard loads)
    }

    // **Test Case 3: Create a new board**
    @Test
    public void test3_CreateNewBoard() {
        test2_SuccessfulLogin(); // Reuse login
        boardPage = new BoardPage(driver);
        boardPage.createNewBoard("boss board");
        // Add assertion (e.g., verify board exists)
    }

    // **Test Case 4: Create a new list**
    @Test
    public void test4_CreateNewList() {
        test3_CreateNewBoard(); // Reuse board creation
        listPage = new ListPage(driver);
        listPage.createList("list 3");
        // Add assertion (e.g., verify list appears)
    }

    // **Test Case 5: Create and edit a card**
    @Test
    public void test5_CreateAndEditCard() {
        test4_CreateNewList(); // Reuse list creation
        cardPage = new CardPage(driver);
        cardPage.createCard("card 4");
        cardPage.editCard("new card 4", "description");
        // Add assertion (e.g., verify card updates)
    }

    // **Test Case 6: Add a comment to a card**
    @Test
    public void test6_AddCommentToCard() {
        test5_CreateAndEditCard(); // Reuse card creation
        cardPage.addComment("comment 5");
        // Add assertion (e.g., verify comment exists)
    }

    // **Test Case 7: Delete a card**
    @Test
    public void test7_DeleteCard() {
        test6_AddCommentToCard(); // Reuse card with comment
        cardPage.deleteCard();
        // Add assertion (e.g., verify card is removed)
    }

    // **Test Case 8: Register a new user**
    @Test
    public void test8_UserRegistration() {
        driver.get("http://localhost:4000/sign_in");
        registrationPage = loginPage.clickCreateAccount();
        registrationPage.register("a", "b", "c@d", "1212", "121212");
        // Add assertion (e.g., verify registration success)
    }
}
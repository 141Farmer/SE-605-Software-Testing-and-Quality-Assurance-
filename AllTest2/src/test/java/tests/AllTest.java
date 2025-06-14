package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.*;
import utils.WaitUtils;



@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AllTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private SignUpPage signUpPage;
    private BoardPage boardPage;
    private CardPage cardPage;

    @Before
    public void setUp() {
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary("/usr/bin/firefox");
        System.setProperty("webdriver.gecko.driver", "/home/winnoer/Applications/geckodriver/geckodriver");

        driver = new FirefoxDriver(options);

        driver.manage().window().setSize(new Dimension(1366, 728));


        loginPage = new LoginPage(driver);
        signUpPage = new SignUpPage(driver);
        boardPage = new BoardPage(driver);
        cardPage = new CardPage(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void test1_defaultLogin() {
        loginPage.openLoginPage();
        loginPage.submitLoginForm();
    }

    @Test
    public void test2_ValidLogin() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
    }

    @Test
    public void test3_InvalidLogin() {
        loginPage.openLoginPage();
        loginPage.login("id@user.com", "wro");
        loginPage.verifyLoginFailed();
    }

    @Test
    public void test4_falseRegistration() {
        signUpPage.openSignUpPage();
        signUpPage.register("John", "Doe", "joh@doe.com", "password123");
        WaitUtils.pause(1000);

        signUpPage.verifySignUpFailed();
    }

    @Test
    public void test5_rightRegistration() {
        signUpPage.openSignUpPage();
        signUpPage.register("John", "Doe", "rakib@jkj.com", "password123");
    }

    @Test
    public void test6_BoardCreation() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Test Board");
    }

    @Test
    public void test7_ListCreation() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Test Board");
        boardPage.createList("To Do");
    }


    @Test
    public void test8_AddCard() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Card Actions Board");
        WaitUtils.pause(1000);

        boardPage.createList("Tasks");
        WaitUtils.pause(1000);

        cardPage.addCard("First Card");
    }

    @Test
    public void test9_EditCard() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Card Actions Board");
        WaitUtils.pause(1000);

        boardPage.createList("Tasks");
        WaitUtils.pause(1000);

        cardPage.addCard("First Card");
        WaitUtils.pause(1000);

        cardPage.editCardTitle("First Card", "Updated Card");
    }

    @Test
    public void test10_AddDescription() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Card Actions Board");
        WaitUtils.pause(1000);

        boardPage.createList("Tasks");
        WaitUtils.pause(1000);

        cardPage.addCard("First Card");
        WaitUtils.pause(1000);

        cardPage.addOrEditDescription("First Card", "Description");
    }

    @Test
    public void test11_DeleteCard() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Deletion Board");
        WaitUtils.pause(1000);

        boardPage.createList("Trash");
        WaitUtils.pause(1000);

        cardPage.addCard("Disposable Card");
        WaitUtils.pause(1000);

        cardPage.deleteCard("Disposable Card");
    }

    @Test
    public void test12_AddComment() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Comment Board");
        boardPage.createList("Ideas");
        WaitUtils.pause(1000);

        cardPage.addCard("Idea Card");
        cardPage.addComment("Idea Card", "Great idea!");
    }

    @Test
    public void test13_AddRightMember() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Team Board");
        WaitUtils.pause(1000);
        boardPage.createList("Assignments");
        WaitUtils.pause(1000);


        WaitUtils.pause(1000);
        boardPage.addMemberToBoard("Task", "g@h");
    }

    @Test
    public void test14_AddFalseMember() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Team Board");
        WaitUtils.pause(1000);
        boardPage.createList("Assignments");
        WaitUtils.pause(1000);

        WaitUtils.pause(1000);
        boardPage.addMemberToBoard("Task", "g@hisjhgjsdg");
        boardPage.verifyAddMemberToBoardFailed();
    }

    @Test
    public void test15_AddTag() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Tag Board");
        boardPage.createList("Tagged");
        WaitUtils.pause(1000);

        cardPage.addCard("Taggable Card");
        cardPage.addTagsToCard("Taggable Card", new String[]{"green"});
        WaitUtils.pause(1000);
    }

    @Test
    public void test16_AddTags() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Tag Board");
        WaitUtils.pause(1000);

        boardPage.createList("Tagged");
        WaitUtils.pause(1000);

        cardPage.addCard("Taggable Card");
        cardPage.addTagsToCard("Taggable Card", new String[]{"green", "yellow", "red", "blue", "purple", "orange"});
        WaitUtils.pause(1000);
    }

    @Test
    public void test17_AddRightMemberToCard() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Team Board");
        WaitUtils.pause(1000);
        boardPage.createList("Assignments");
        WaitUtils.pause(1000);


        WaitUtils.pause(1000);
        boardPage.addMemberToBoard("Task", "g@h");

        cardPage.addCard("Task");
        cardPage.addMemberToCard("Task", "g@h.com");
    }

    @Test
    public void test18_AddFalseMemberToCard() {
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.createBoard("Team Board");
        WaitUtils.pause(1000);
        boardPage.createList("Assignments");
        WaitUtils.pause(1000);


        WaitUtils.pause(1000);
        boardPage.addMemberToBoard("Task", "g@h");

        cardPage.addCard("Task");
        cardPage.addMemberToCard("Task", "g@h564675.com");
        cardPage.verifyAddMemberToCardFailed();
    }

    @Test
    public void test19_signOut(){
        loginPage.openLoginPage();
        loginPage.login("c@d", "12345");
        WaitUtils.pause(1000);

        boardPage.signOut();
    }
}

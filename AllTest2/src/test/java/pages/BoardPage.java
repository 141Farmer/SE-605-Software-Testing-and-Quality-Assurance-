package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BoardPage {
    private WebDriver driver;


    private By addNewBoardButton = By.id("add_new_board");
    private By boardNameField = By.id("board_name");
    private By submitBoardButton = By.cssSelector("button");

    private By addNewListButton = By.cssSelector(".inner");  // Based on original code
    private By listNameField = By.id("list_name");
    private By submitListButton = By.cssSelector("button");

    private By addMemberButton = By.cssSelector("li > .add-new"); // Selector for adding member on card
    private By memberEmailField = By.id("crawljax_member_email");
    private By submitMemberButton = By.cssSelector("button");
    private By cancelAddMemberLink = By.linkText("cancel");
    private By signOutButton = By.id("crawler-sign-out");


    public BoardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void createBoard(String boardName) {
        driver.findElement(addNewBoardButton).click();
        driver.findElement(boardNameField).clear();
        driver.findElement(boardNameField).sendKeys(boardName);
        driver.findElement(submitBoardButton).click();
    }

    public void createList(String listName) {
        driver.findElement(addNewListButton).click();
        driver.findElement(listNameField).clear();
        driver.findElement(listNameField).sendKeys(listName);
        driver.findElement(submitListButton).click();
    }

    public void addMemberToBoard(String boardName, String email) {
        driver.findElement(addMemberButton).click();
        driver.findElement(memberEmailField).clear();
        driver.findElement(memberEmailField).sendKeys(email);
        driver.findElement(submitMemberButton).click();
    }

    private By boardMemberError = By.cssSelector(".error");

    public void verifyAddMemberToBoardFailed() {
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(boardMemberError));
        assert error.isDisplayed();
    }

    public void signOut() {
        driver.findElement(signOutButton).click();
    }

}

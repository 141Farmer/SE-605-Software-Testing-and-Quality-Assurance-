package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardPage extends BasePage {
    @FindBy(id = "add_new_board")
    private WebElement addNewBoardButton;

    @FindBy(id = "board_name")
    private WebElement boardNameField;

    @FindBy(css = "#boards_nav span")
    private WebElement boardsNavLink;

    @FindBy(css = "#crawler-sign-out > span")
    private WebElement signOutButton;

    public BoardPage(WebDriver driver) {
        super(driver);
    }

    public void createNewBoard(String boardName) {
        click(addNewBoardButton);
        type(boardNameField, boardName);
        click(submitButton);
    }

    public void signOut() {
        click(signOutButton);
    }
}
package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPage extends BasePage {
    @FindBy(linkText = "Add a new card...")
    private WebElement addNewCardLink;

    @FindBy(id = "card_name")
    private WebElement cardNameField;

    @FindBy(css = ".card-content")
    private WebElement cardContent;

    @FindBy(css = "textarea")
    private WebElement commentField;

    @FindBy(linkText = "Edit")
    private WebElement editLink;

    @FindBy(css = ".fa-trash-o")
    private WebElement deleteButton;

    @FindBy(css = ".fa-plus")
    private WebElement addMemberButton;

    @FindBy(id = "crawljax_member_email")
    private WebElement memberEmailField;

    public CardPage(WebDriver driver) {
        super(driver);
    }

    public void createCard(String cardName) {
        click(addNewCardLink);
        type(cardNameField, cardName);
        click(submitButton);
    }

    public void addComment(String comment) {
        click(cardContent);
        type(commentField, comment);
        click(submitButton);
    }

    public void editCard(String newName, String description) {
        click(editLink);
        type(cardNameField, newName);
        type(descriptionField, description);
        click(updateButton);
    }

    public void deleteCard() {
        click(deleteButton);
    }

    public void addMember(String email) {
        click(addMemberButton);
        type(memberEmailField, email);
        click(submitButton);
    }
}
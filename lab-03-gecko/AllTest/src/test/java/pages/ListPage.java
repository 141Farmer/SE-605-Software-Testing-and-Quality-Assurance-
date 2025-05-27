package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ListPage extends BasePage {
    @FindBy(css = ".inner")
    private WebElement addListButton;

    @FindBy(id = "list_name")
    private WebElement listNameField;

    @FindBy(css = ".add-new > .inner")
    private WebElement addNewListButton;

    public ListPage(WebDriver driver) {
        super(driver);
    }

    public void createList(String listName) {
        click(addListButton);
        type(listNameField, listName);
        click(submitButton);
    }
}

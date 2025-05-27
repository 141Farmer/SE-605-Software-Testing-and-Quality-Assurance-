package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage {
    @FindBy(id = "user_first_name")
    private WebElement firstNameField;

    @FindBy(id = "user_last_name")
    private WebElement lastNameField;

    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(id = "user_password_confirmation")
    private WebElement passwordConfirmationField;

    @FindBy(css = "button")
    private WebElement submitButton;

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void register(String firstName, String lastName, String email, String password, String passwordConfirmation) {
        type(firstNameField, firstName);
        type(lastNameField, lastName);
        type(emailField, email);
        type(passwordField, password);
        type(passwordConfirmationField, passwordConfirmation);
        click(submitButton);
    }
}

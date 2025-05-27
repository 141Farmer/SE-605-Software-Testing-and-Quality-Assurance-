package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "user_email")
    private WebElement emailField;

    @FindBy(id = "user_password")
    private WebElement passwordField;

    @FindBy(css = "button")
    private WebElement submitButton;

    @FindBy(linkText = "Create new account")
    private WebElement createAccountLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        type(emailField, email);
        type(passwordField, password);
        click(submitButton);
    }

    public RegistrationPage clickCreateAccount() {
        click(createAccountLink);
        return new RegistrationPage(driver);
    }
}

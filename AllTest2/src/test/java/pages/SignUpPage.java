package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SignUpPage {
    private WebDriver driver;


    private By firstNameField = By.id("user_first_name");
    private By lastNameField = By.id("user_last_name");
    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By passwordConfirmationField = By.id("user_password_confirmation");
    private By signUpButton = By.cssSelector("button");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openSignUpPage() {
        driver.get("http://localhost:4000/sign_up"); // Adjust if different
    }

    public void register(String firstName, String lastName, String email, String password) {
        driver.findElement(firstNameField).clear();
        driver.findElement(firstNameField).sendKeys(firstName);

        driver.findElement(lastNameField).clear();
        driver.findElement(lastNameField).sendKeys(lastName);

        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);

        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(passwordConfirmationField).clear();
        driver.findElement(passwordConfirmationField).sendKeys(password);

        driver.findElement(signUpButton).click();
    }

    private By signUpError = By.cssSelector(".error");

    public void verifySignUpFailed() {
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(signUpError));
        assert error.isDisplayed();
    }


}

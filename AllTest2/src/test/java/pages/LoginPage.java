package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;


    private By emailField = By.id("user_email");
    private By passwordField = By.id("user_password");
    private By loginButton = By.cssSelector("button");
    private By loginError = By.cssSelector(".error");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginPage() {
        driver.get("http://localhost:4000/sign_in");
    }

    public void submitLoginForm() {
        driver.findElement(loginButton).click();
    }


    public void login(String email, String password) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public void verifyLoginFailed() {
        WebElement error = driver.findElement(loginError);
        assert error.isDisplayed();
    }
}

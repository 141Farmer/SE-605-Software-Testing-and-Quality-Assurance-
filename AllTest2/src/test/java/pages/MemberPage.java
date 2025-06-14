package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MemberPage {
    private WebDriver driver;


    private By addNewMemberButton = By.cssSelector("li > .add-new");
    private By memberEmailField = By.id("crawljax_member_email");
    private By submitMemberButton = By.cssSelector("button");
    private By cancelLink = By.linkText("cancel");

    public MemberPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addMember(String email) {
        driver.findElement(addNewMemberButton).click();
        driver.findElement(memberEmailField).clear();
        driver.findElement(memberEmailField).sendKeys(email);
        driver.findElement(submitMemberButton).click();
    }

    public void cancelAddMember() {
        driver.findElement(cancelLink).click();
    }
}

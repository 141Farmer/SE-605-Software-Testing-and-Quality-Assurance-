package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CardPage {
    private WebDriver driver;


    private By addNewCardLink = By.linkText("Add a new card...");
    private By cardNameField = By.id("card_name");
    private By submitCardButton = By.cssSelector("button");

    private By cardContent = By.cssSelector(".card-content");
    private By cardById(String cardId) {
        return By.cssSelector("#card_" + cardId + " > .card-content");
    }

    private By cardByName(String cardName) {
        return By.xpath("//div[contains(@class, 'card-content') and contains(., '" + cardName + "')]");
    }

    private By deleteCardButton = By.cssSelector(".fa-trash-o");

    private By editLink = By.linkText("Edit");
    private By editCardInput = By.cssSelector("input");
    private By descriptionTextarea = By.cssSelector("textarea:nth-child(2)");
    private By saveEditButton = By.cssSelector("button:nth-child(3)");

    private By closeButton = By.cssSelector(".fa-close");

    private By commentTextarea = By.cssSelector("textarea");
    private By addCommentButton = By.cssSelector("button");

    private By addMemberButton = By.cssSelector("li > .add-new"); // Selector for adding member on card
    private By memberEmailField = By.id("crawljax_member_email");
    private By submitMemberButton = By.cssSelector("button");
    private By cancelAddMemberLink = By.linkText("cancel");

    private By tagsLink = By.linkText("Tags");
    private By greenTag = By.cssSelector(".green");
    private By yellowTag = By.cssSelector(".yellow");
    private By orangeTag = By.cssSelector(".orange");
    private By redTag = By.cssSelector(".red");
    private By purpleTag = By.cssSelector(".purple");
    private By blueTag = By.cssSelector(".blue");
    private By closeTagPopup = By.cssSelector(".close:nth-child(2) > .fa");

    public CardPage(WebDriver driver) {
        this.driver = driver;
    }

    public void addCard(String cardName) {
        driver.findElement(addNewCardLink).click();
        driver.findElement(cardNameField).clear();
        driver.findElement(cardNameField).sendKeys(cardName);
        driver.findElement(submitCardButton).click();
    }

    public void deleteCard(String cardName) {
        driver.findElement(cardByName(cardName)).click();
        driver.findElement(deleteCardButton).click();
    }

    public void editCardTitle(String cardName, String newTitle) {
        driver.findElement(cardByName(cardName)).click();
        driver.findElement(editLink).click();
        driver.findElement(editCardInput).clear();
        driver.findElement(editCardInput).sendKeys(newTitle);
        driver.findElement(saveEditButton).click();
    }

    public void addOrEditDescription(String cardName, String description) {
        driver.findElement(cardByName(cardName)).click();
        driver.findElement(editLink).click();
        driver.findElement(descriptionTextarea).clear();
        driver.findElement(descriptionTextarea).sendKeys(description);
        driver.findElement(saveEditButton).click();
    }



    public void addComment(String cardName, String comment) {
        driver.findElement(cardByName(cardName)).click();

        driver.findElement(commentTextarea).clear();
        driver.findElement(commentTextarea).sendKeys(comment);

        driver.findElement(addCommentButton).click();

        driver.findElement(closeButton).click();
    }

    public void addMemberToCard(String cardName, String email) {
        driver.findElement(cardByName(cardName)).click();
        driver.findElement(addMemberButton).click();
        driver.findElement(memberEmailField).clear();
        driver.findElement(memberEmailField).sendKeys(email);
        driver.findElement(submitMemberButton).click();
    }

    public void addTagsToCard(String cardName, String[] tags) {
        driver.findElement(cardByName(cardName)).click();
        driver.findElement(tagsLink).click();

        for (String tag : tags) {
            switch (tag.toLowerCase()) {
                case "green":
                    driver.findElement(greenTag).click();
                    break;
                case "yellow":
                    driver.findElement(yellowTag).click();
                    break;
                case "orange":
                    driver.findElement(orangeTag).click();
                    break;
                case "red":
                    driver.findElement(redTag).click();
                    break;
                case "purple":
                    driver.findElement(purpleTag).click();
                    break;
                case "blue":
                    driver.findElement(blueTag).click();
                    break;
            }

        }

        driver.findElement(closeTagPopup).click();
    }

    private By cardMemberError = By.cssSelector(".error");

    public void verifyAddMemberToCardFailed() {
        WebElement error = new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(cardMemberError));
        assert error.isDisplayed();
    }

}

package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class AccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickContinue() {
        WebElement button = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Continue')]")));
        button.click();
    }

    public boolean verifyLoggedInAsUsername() {
        WebElement element = wait
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Logged in as')]")));
        return element.isDisplayed();
    }

    public void clickDeleteAccount() {
        WebElement button = wait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Delete Account')]")));
        button.click();
    }

    public boolean verifyAccountDeletedVisible() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ACCOUNT DELETED!')]")));
        return element.isDisplayed();
    }
}
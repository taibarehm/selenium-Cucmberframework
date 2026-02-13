package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signupLoginBy = By.xpath("//a[normalize-space()='Signup / Login']");
    private By deleteAccountBy = By.xpath("//a[contains(text(),'Delete Account')]");
    private By loggedInAsBy = By.xpath("//*[contains(text(),' Logged in as ')]");
    private JSONObject json;
    private JavascrUtility jsUtil;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.jsUtil = new JavascrUtility(driver);
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean verifyHomePage() {
        try {
            String title = driver.getTitle();
            return title != null && title.contains("Automation Exercise");
        } catch (Exception e) {
            System.out.println("verifyHomePage: exception=" + e.getMessage());
            return false;
        }
    }

    public boolean clickSignupLogin() {
        try {
            WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signupLoginBy));
            if (button.isDisplayed()) {
                jsUtil.highlightElement(button);
                button.click();
                return true;
            }
        } catch (Exception e) {
            System.out.println("clickSignupLogin: exception=" + e.getMessage());
        }
        return false;
    }

    public boolean isDeleteAccountVisible() {
        try {
            WebElement deleteAccountElement = wait.until(
                    ExpectedConditions.elementToBeClickable(deleteAccountBy));
            jsUtil.highlightElement(deleteAccountElement);
            deleteAccountElement.click();
            return true;
        } catch (Exception e) {
            System.out.println("clickDeleteAccount: exception=" + e.getMessage());
            return false;
        }
    }

    public boolean isLoggedInAsVisibleUserName(String username) {

        boolean status = false;
        json = new JSONObject(username);
        String actualUsername = json.getString("username");
        try {
            WebElement loggedInAsElement = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(loggedInAsBy));
            jsUtil.highlightElement(loggedInAsElement);
            String actualText = loggedInAsElement.getText();
            if (actualText.contains(actualUsername)) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println("isLoggedInAsVisibleUserName: exception=" + e.getMessage());
            return false;
        }
        return status;
    }

}

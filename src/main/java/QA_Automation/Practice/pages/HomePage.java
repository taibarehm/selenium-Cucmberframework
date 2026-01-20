package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By signupLoginBy = By.xpath("//a[contains(normalize-space(),'Signup / Login')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateTo(String url) {
        driver.get(url);
    }

    public boolean verifyHomePage() {
        try {
            System.out.println("in the method verifyHome");
            String title = driver.getTitle();
            boolean result = false;
            if (title != null && title.contains("Automation Exercise")) {
                System.out.println("verifyHomePage: actual title='" + title + "'");
                result = true;
            }

            return result;
        } catch (Exception e) {
            System.out.println("verifyHomePage: exception=" + e.getMessage());
            return false;
        }
    }

    public boolean clickSignupLogin() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(signupLoginBy));
        if (button.isDisplayed()) {
            System.out.println("Signup/Login button is visible");
            button.click();
            return true;
        }
        return false;
    }

}

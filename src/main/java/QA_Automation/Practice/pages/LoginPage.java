package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebElement nameField = driver.findElement(By.xpath("//input[@placeholder='Name']"));
    private WebElement buttonSignupemailField = driver
            .findElement(By.xpath("//input[@placeholder='Email Address' and @data-qa='signup-email']"));
    private WebElement buttonSignup = driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
    private WebElement button = driver.findElement(By.xpath("//button[contains(text(),'Signup')]"));
    private WebElement NewUserSignup = wait
            .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'New User Signup!')]")));
    private WebElement loginEmailField = driver.findElement(By.xpath("//input[@data-qa='login-email']"));
    private WebElement passwordField = driver.findElement(By.xpath("//input[@data-qa='login-password']"));

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }

    public boolean enterNameAndEmail(String name, String email) throws Exception {
        boolean status = false;

        if (nameField.isDisplayed() && buttonSignupemailField.isDisplayed()) {
            nameField.sendKeys(name);
            buttonSignupemailField.sendKeys(email);

            status = true;
            return status;
        }
        return status;

    }

    public boolean clickSignup() throws Exception {
        boolean status = false;

        try {
            if (buttonSignup.isDisplayed() || buttonSignup.isEnabled()) {
                buttonSignup.click();
                status = true;
            }
            return status;
        } catch (Exception e) {
            System.out.println("Exception in clickSignup: " + e.getMessage());
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", button);
            return status = true;
        }
    }

    public boolean verifyEnterAccountInformationVisible() {
        WebElement element = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//*[contains(text(),'ENTER ACCOUNT INFORMATION')]")));
        return element.isDisplayed();
    }

    public void enterLoginEmailAndPassword(String email, String password) {
        loginEmailField.sendKeys(email);

        passwordField.sendKeys(password);
    }

    public boolean isnewUserSignupVisible() {

        NewUserSignup.isDisplayed();
        boolean status = NewUserSignup.isDisplayed();
        System.out.println("New User Signup! " + status);
        return status;
    }

}
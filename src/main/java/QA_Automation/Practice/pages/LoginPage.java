package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.checkerframework.checker.units.qual.s;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascrUtility jsUtil;

    // Locators
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By signupEmailField = By.xpath("//input[@placeholder='Email Address' and @data-qa='signup-email']");
    private By signupButton = By.xpath("//button[contains(text(),'Signup')]");
    private By newUserSignupText = By.xpath("//*[contains(text(),'New User Signup!')]");
    private By loginEmailField = By.xpath("//input[@data-qa='login-email']");
    private By passwordField = By.xpath("//input[@data-qa='login-password']");

    private By loginAccountText = By.xpath("//h2[contains(text(),'Login to your account')]");
    private By loginEmailFieldQA = By.xpath("//input[@data-qa='login-email']");
    private By passwordFieldQA = By.xpath("//input[@data-qa='login-password']");
    private By loginButtonQA = By.xpath("//button[@data-qa='login-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        jsUtil = new JavascrUtility(driver);
        jsUtil.removeAds();
    }

    public boolean enterNameAndEmail(String name, String email) {
        jsUtil.removeAds();
        WebElement nameInput = driver.findElement(nameField);
        WebElement emailInput = driver.findElement(signupEmailField);

        if (nameInput.isDisplayed() && emailInput.isDisplayed()) {
            jsUtil.highlightElement(nameInput);
            jsUtil.highlightElement(emailInput);
            nameInput.sendKeys(name);
            emailInput.sendKeys(email);
            return true;
        }
        return false;
    }

    public boolean clickSignup() {
        WebElement signupBtn = driver.findElement(signupButton);
        try {
            if (signupBtn.isDisplayed() && signupBtn.isEnabled()) {
                jsUtil.highlightElement(signupBtn);
                signupBtn.click();
                return true;
            }
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", signupBtn);
            return true;
        }
        return false;
    }

    public boolean isnewUserSignupVisible() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(newUserSignupText));
        jsUtil.highlightElement(element);
        return element.isDisplayed();
    }

    public boolean isLoginAccountVisible() throws Exception {

        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(loginAccountText));
            jsUtil.highlightElement(element);
            return element.isDisplayed();
        } catch (Exception e) {
            throw new Exception("Login account text is not visible");
        }
    }

    public boolean enterLoginEmailAndPassword(String input) throws Exception {
        jsUtil.removeAds();
        boolean status = false;

        JSONObject jsonObject = new JSONObject(input);
        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");
        WebElement emailInput = driver.findElement(loginEmailFieldQA);
        WebElement passwordInput = driver.findElement(passwordFieldQA);
        if (emailInput.isDisplayed() && passwordInput.isDisplayed()) {
            jsUtil.highlightElement(emailInput);
            jsUtil.highlightElement(passwordInput);
            emailInput.sendKeys(email);
            passwordInput.sendKeys(password);
            status = true;
        }
        return status;

    }

    public boolean clickLoginButton() {
        WebElement loginBtn = driver.findElement(loginButtonQA);
        try {
            if (loginBtn.isDisplayed() && loginBtn.isEnabled()) {
                jsUtil.highlightElement(loginBtn);
                loginBtn.click();
                return true;
            }
        } catch (Exception e) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", loginBtn);
            return true;
        }
        return false;
    }
}

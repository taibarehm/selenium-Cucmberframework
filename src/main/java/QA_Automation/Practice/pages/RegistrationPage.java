package QA_Automation.Practice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class RegistrationPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillAccountDetails() {
        // Select title
        WebElement title = driver.findElement(By.id("id_gender1"));
        title.click();

        // Password
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("password123");

        // Date of birth
        WebElement day = driver.findElement(By.id("days"));
        day.sendKeys("15");

        WebElement month = driver.findElement(By.id("months"));
        month.sendKeys("January");

        WebElement year = driver.findElement(By.id("years"));
        year.sendKeys("1990");
    }

    public void selectNewsletterCheckbox() {
        WebElement checkbox = driver
                .findElement(By.xpath("//label[contains(text(),'Sign up for our newsletter')]/input"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void selectOffersCheckbox() {
        WebElement checkbox = driver
                .findElement(By.xpath("//label[contains(text(),'Receive special offers from our partners')]/input"));
        if (!checkbox.isSelected()) {
            checkbox.click();
        }
    }

    public void fillAddressDetails() {
        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys("Test");

        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys("User");

        WebElement company = driver.findElement(By.id("company"));
        company.sendKeys("Test Company");

        WebElement address = driver.findElement(By.id("address1"));
        address.sendKeys("123 Test Street");

        WebElement address2 = driver.findElement(By.id("address2"));
        address2.sendKeys("Apt 456");

        WebElement country = driver.findElement(By.id("country"));
        country.sendKeys("United States");

        WebElement state = driver.findElement(By.id("state"));
        state.sendKeys("California");

        WebElement city = driver.findElement(By.id("city"));
        city.sendKeys("Los Angeles");

        WebElement zipcode = driver.findElement(By.id("zipcode"));
        zipcode.sendKeys("90001");

        WebElement mobile = driver.findElement(By.id("mobile_number"));
        mobile.sendKeys("1234567890");
    }

    public void clickCreateAccount() {
        WebElement button = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Create Account')]")));
        button.click();
    }

    public boolean verifyAccountCreatedVisible() {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'ACCOUNT CREATED!')]")));
        return element.isDisplayed();
    }

    public String getFormTitle() {

        WebElement titleElement = wait.until(
                ExpectedConditions
                        .visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Enter Account Information')]")));
        return titleElement.getText();
    }
}
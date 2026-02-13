package QA_Automation.Practice.pages;

import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private JavascrUtility jsUtil;

    /* ================= LOCATORS ================= */

    private By formTitle = By.xpath("//b[contains(text(),'Enter Account Information')]");
    private By form = By.xpath("//form[@action='/signup']");

    private By titleMr = By.xpath("//input[@value='Mr']");
    private By titleMrs = By.xpath("//input[@value='Mrs']");

    private By password = By.id("password");
    private By day = By.id("days");
    private By month = By.id("months");
    private By year = By.id("years");

    private By newsletterCheckbox = By.xpath("//label[normalize-space()='Sign up for our newsletter!']");
    private By offersCheckbox = By.xpath("//label[contains(text(),'Receive special offers from our partners!')]");

    private By firstName = By.id("first_name");
    private By lastName = By.id("last_name");
    private By company = By.id("company");
    private By address1 = By.id("address1");
    private By address2 = By.id("address2");
    private By country = By.id("country");
    private By state = By.id("state");
    private By city = By.id("city");
    private By zipcode = By.id("zipcode");
    private By mobile = By.id("mobile_number");

    private By createAccountButton = By.xpath("//button[contains(text(),'Create Account')]");
    private By accountCreatedText = By.xpath("//b[contains(text(),'Account Created!')]");
    private By continueButton = By.xpath("//a[contains(text(),'Continue')]");
    private By accountDeletedText = By.xpath("//b[contains(text(),'Account Deleted!')]");

    /* ================= CONSTRUCTOR ================= */

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.jsUtil = new JavascrUtility(driver);
    }

    /* ================= METHODS ================= */

    public boolean fillAccountDetails(String input) {

        JSONObject json = new JSONObject(input);
        String name = json.getString("name");
        String email = json.getString("email");
        String titleText = json.getString("title");
        String pwd = json.getString("password");
        String d = json.getString("day");
        String m = json.getString("month");
        String y = json.getString("year");

        WebElement titleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(formTitle));

        if (titleElement.isDisplayed() &&
                driver.findElement(password).isDisplayed() && driver.findElement(day).isDisplayed()
                && driver.findElement(month).isDisplayed()
                && driver.findElement(year).isDisplayed()) {
            String script = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});";
            jsUtil.executeJS(script, titleElement);
            jsUtil.highlightElement(titleElement);

            if (titleText.equalsIgnoreCase("Mr")) {
                WebElement mr = driver.findElement(titleMr);
                jsUtil.highlightElement(mr);
                jsUtil.executeJS(script, mr);
                mr.click();
            } else {
                WebElement mrs = driver.findElement(titleMrs);
                jsUtil.highlightElement(mrs);
                jsUtil.executeJS(script, mrs);
                mrs.click();
            }
            jsUtil.highlightElement(driver.findElement(password));
            jsUtil.executeJS(script, driver.findElement(password));
            driver.findElement(password).sendKeys(pwd);
            jsUtil.highlightElement(driver.findElement(day));
            jsUtil.executeJS(script, driver.findElement(day));
            driver.findElement(day).sendKeys(d);
            jsUtil.highlightElement(driver.findElement(month));
            jsUtil.executeJS(script, driver.findElement(month));
            driver.findElement(month).sendKeys(m);
            jsUtil.highlightElement(driver.findElement(year));
            jsUtil.executeJS(script, driver.findElement(year));

            driver.findElement(year).sendKeys(y);

            return true;
        }
        return false;
    }

    public boolean selectNewsletterCheckbox(boolean isNewsletter) {
        if (isNewsletter) {
            WebElement checkbox = driver.findElement(newsletterCheckbox);
            jsUtil.highlightElement(checkbox);
            try {
                checkbox.click();
            } catch (Exception e) {
                // If the ad is still blocking, JS click will bypass it
                jsUtil.executeJS("arguments[0].click();", checkbox);
            }
            return true;
        }
        return false;
    }

    public boolean selectOffersCheckbox(boolean isOffers) {
        if (isOffers) {
            WebElement checkbox = driver.findElement(offersCheckbox);
            jsUtil.highlightElement(checkbox);
            try {
                checkbox.click();
            } catch (Exception e) {
                // If the ad is still blocking, JS click will bypass it
                jsUtil.executeJS("arguments[0].click();", checkbox);
            }
            return true;
        }
        return false;
    }

    public boolean fillAddressDetails(String input) {

        JSONObject json = new JSONObject(input);
        WebElement first_Name = driver.findElement(firstName);
        WebElement last_name = driver.findElement(lastName);
        WebElement companyName = driver.findElement(company);
        WebElement address_Line1 = driver.findElement(address1);
        WebElement address_Line2 = driver.findElement(address2);
        WebElement countryElement = driver.findElement(country);
        WebElement stateElement = driver.findElement(state);
        WebElement cityElement = driver.findElement(city);
        WebElement zipcodeElement = driver.findElement(zipcode);
        WebElement mobileElement = driver.findElement(mobile);
        if (first_Name.isDisplayed() && last_name.isDisplayed() && companyName.isDisplayed()
                && address_Line1.isDisplayed() &&
                address_Line2.isDisplayed() && countryElement.isDisplayed() && stateElement.isDisplayed()
                && cityElement.isDisplayed() &&
                zipcodeElement.isDisplayed() && mobileElement.isDisplayed()) {
            String script = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});";

            jsUtil.executeJS(script, first_Name);
            jsUtil.highlightElement(first_Name);
            first_Name.sendKeys(json.getString("first_name"));
            jsUtil.executeJS(script, last_name);
            jsUtil.highlightElement(last_name);
            last_name.sendKeys(json.getString("last_name"));
            jsUtil.executeJS(script, companyName);
            jsUtil.highlightElement(companyName);
            companyName.sendKeys(json.getString("company"));
            jsUtil.executeJS(script, address_Line1);
            jsUtil.highlightElement(address_Line1);
            address_Line1.sendKeys(json.getString("address_line_1"));
            jsUtil.executeJS(script, address_Line2);
            jsUtil.highlightElement(address_Line2);
            address_Line2.sendKeys(json.getString("address_line_2"));
            jsUtil.executeJS(script, countryElement);
            jsUtil.highlightElement(countryElement);
            countryElement.sendKeys(json.getString("country"));
            jsUtil.executeJS(script, stateElement);
            jsUtil.highlightElement(stateElement);
            stateElement.sendKeys(json.getString("state"));
            jsUtil.executeJS(script, cityElement);
            jsUtil.highlightElement(cityElement);
            cityElement.sendKeys(json.getString("city"));
            jsUtil.executeJS(script, zipcodeElement);
            jsUtil.highlightElement(zipcodeElement);
            zipcodeElement.sendKeys(json.getString("zipcode"));
            jsUtil.executeJS(script, mobileElement);
            jsUtil.highlightElement(mobileElement);
            mobileElement.sendKeys(json.getString("mobile_number"));

            return true;
        }
        return false;
    }

    public boolean clickCreateAccount() throws Exception {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(createAccountButton));

        if (button.isDisplayed()) {

            jsUtil.highlightElement(button);
            String script = "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});";
            jsUtil.executeJS(script, button);
            button.click();
            return true;
        }
        return false;
    }

    public boolean verifyAccountCreatedVisible() {

        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountCreatedText));

        jsUtil.highlightElement(element);
        return element.isDisplayed();
    }

    public boolean clickContinueButton() throws Exception {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        if (button.isDisplayed()) {
            jsUtil.highlightElement(button);
            // String script = "arguments[0].scrollIntoView({behavior: 'smooth', block:
            // 'center'});";
            // jsUtil.executeJS(script, button);

            button.click();
            return true;
        }
        return false;
    }

    public boolean isDeleteAccountVisible() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(accountDeletedText));

        jsUtil.highlightElement(element);
        return element.isDisplayed();
    }
}
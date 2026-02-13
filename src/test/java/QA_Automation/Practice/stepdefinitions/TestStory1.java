package QA_Automation.Practice.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.json.JSONObject;
import org.checkerframework.checker.units.qual.s;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;
import QA_Automation.Practice.pages.HomePage;
import QA_Automation.Practice.pages.LoginPage;
import QA_Automation.Practice.pages.RegistrationPage;
import QA_Automation.Practice.pages.AccountPage;

import QA_Automation.Practice.utils.ExcelTestExecutor;
import java.util.List;
import java.util.Map;

public class TestStory1 {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ExcelTestExecutor excelTestExecutor = new ExcelTestExecutor();

    private RegistrationPage registrationPage;
    // private AccountPage accountPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);

        registrationPage = new RegistrationPage(driver);
        // accountPage = new AccountPage(driver);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error closing driver: " + e.getMessage());
            }
        }
    }

    int cout = 0;
    private String step;
    private String expected;

    @Given("Launch browser and go to {string}")
    public void i_launch_the_browser(String url) throws Exception {
        String status = "fail";
        step = "Launch browser and go to " + url;
        String currentUrl = driver.getCurrentUrl();
        if (url != null && !url.equals(currentUrl)) {
            System.out.println("Browser opens successfully.");

            status = "Pass";

        }
        excelTestExecutor.runTests(step, status);

        System.out.println("Browser is  not  launched");
    }

    @Then("I verify that home page is visible successfully")
    public void i_verify_home_page_visible() throws Exception {
        step = "Observe the home page";
        String status = "fail";
        boolean isHomePageVisible = homePage.verifyHomePage();
        if (isHomePageVisible) {
            status = "Pass";

        }
        assert isHomePageVisible : "Home page is not visible";
        System.out.println("Home page is visible successfully");
        excelTestExecutor.runTests(step, status);
    }

    @When("I click on Signup_Login button")
    public void i_click_on_button() throws Exception {

        step = "Click on 'Signup / Login' button";
        String status = "fail";
        boolean isClicked = homePage.clickSignupLogin();
        assert isClicked : "Signup/Login button not clicked";
        System.out.println("Signup/Login button clicked");
        if (isClicked) {
            status = "Pass";

        }

        excelTestExecutor.runTests(step, status);

    }

    @Then("I verify that {string} text is displayed on the page")
    public void i_verify_that_is_visible(String elementName) throws Exception {
        step = "Verify visibility of New User Signup";
        String status = "fail";
        boolean isVisible = false;
        try {
            isVisible = loginPage.isnewUserSignupVisible();
            if (isVisible) {
                status = "Pass";

            }
            assert isVisible : elementName + " is not visible";
            System.out.println(elementName + " is visible");
            excelTestExecutor.runTests(step, status);

        } catch (Exception e) {
            System.err.println("Error checking visibility of element: " + e.getMessage());
        }

    }

    @And("Enter Name and Email address and click 'Signup'")
    public void i_enter_name_and_email() throws Exception {
        String stepName = "Enter Name and Email address and click 'Signup'";
        String status = "fail";
        String input = excelTestExecutor.getInput(stepName);

        JSONObject json = new JSONObject(input);

        String name = json.getString("name");
        String email = json.getString("email");
        System.out.println("Name: " + name + ", Email: " + email);
        System.out.println("Entered name and email");
        if (loginPage.enterNameAndEmail(name, email) && loginPage.clickSignup()) {
            status = "Pass";
            System.out.println("Clicked Signup button with status: " + status);
        }
        excelTestExecutor.runTests(stepName, status);
    }

    // @Then("{string} form is visible")
    // public void form_is_visible(String expectedText) throws Exception {
    // step = "Verify 'ENTER ACCOUNT INFORMATION' is visible";
    // String status = "fail";

    // // Assuming registrationPage has a method to check the header text
    // boolean isVisible =
    // registrationPage.getFormTitle().equalsIgnoreCase(expectedText);

    // if (isVisible) {
    // status = "Pass";
    // }

    // excelTestExecutor.runTests(step, status);
    // assert isVisible : expectedText + " was not visible!";
    // }

    @Then("Fill Title, Name, Email, Password, Date of Birth")
    public void fields_are_populated_correctly() throws Exception {
        step = "Fill Title, Name, Email, Password, Date of Birth";
        String status = "fail";
        String input = excelTestExecutor.getInput(step);
        if (registrationPage.fillAccountDetails(input)) {
            {
                status = "Pass";
            }
            excelTestExecutor.runTests(step, status);
            Thread.sleep(2000);
        }
    }

    @When("Select Newsletter and Special Offers checkboxes")
    public void select_checkboxes() throws Exception {
        String stepName = "Select Newsletter and Special Offers checkboxes";
        String status = "fail";
        String input = excelTestExecutor.getInput(stepName);
        JSONObject json = new JSONObject(input);

        boolean isNewsletter = json.getBoolean("newsletter");
        boolean isOffers = json.getBoolean("partner_offers");
        boolean newsletterSelected = registrationPage.selectNewsletterCheckbox(isNewsletter);
        boolean offersSelected = registrationPage.selectOffersCheckbox(isOffers);

        if (newsletterSelected && offersSelected) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @Then("Fill Address, Country, State, City, Zipcode, Mobile")
    public void address_details_are_filled() throws Exception {

        String stepName = "Fill Address, Country, State, City, Zipcode, Mobile";
        String status = "fail";
        String input = excelTestExecutor.getInput(stepName);

        if (registrationPage.fillAddressDetails(input)) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);

    }

    @And("Click 'Create Account' button")
    public void click_create_account() throws Exception {
        String stepName = "Click 'Create Account' button";
        String status = "fail";

        if (registrationPage.clickCreateAccount()) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @Then("Click 'Continue' button")
    public void click_continue_button() throws Exception {
        String stepName = "Click 'Continue' button";
        String status = "fail";

        if (registrationPage.verifyAccountCreatedVisible() && registrationPage.clickContinueButton()) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @Then("Click 'Continue to Delete' button")
    public void click_continue_to_delete_button() throws Exception {
        String stepName = "Click 'Continue' button";
        String status = "fail";

        if (registrationPage.isDeleteAccountVisible() && registrationPage.clickContinueButton()) {

            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        // Thread.sleep(2000);
    }

    @When("Click 'Delete Account' button")
    public void click_delete_account_button() throws Exception {
        String stepName = "Click 'Delete Account' button";
        String status = "fail";

        //
        if (homePage.isDeleteAccountVisible()) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @And("Verify 'Login to your account' is visible")
    public void verify_login_account_visible() throws Exception {
        String stepName = "Verify 'Login to your account' is visible";
        String status = "fail";

        if (loginPage.isLoginAccountVisible()) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @When("Enter correct email address and password")
    public void enter_email_and_password() throws Exception {
        String stepName = "Enter correct email address and password";
        String status = "fail";
        String input = excelTestExecutor.getInput(stepName);

        if (loginPage.enterLoginEmailAndPassword(input)) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @Then("Click 'login' button")
    public void click_login_button() throws Exception {
        String stepName = "Click 'login' button";
        String status = "fail";

        if (loginPage.clickLoginButton()) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

    @And("Verify that 'Logged in as username' is visible")
    public void verify_logged_in_as_username_visible() throws Exception {
        String stepName = "Verify that 'Logged in as username' is visible";
        String status = "fail";
        String input = excelTestExecutor.getInput(stepName);

        if (homePage.isLoggedInAsVisibleUserName(input)) {
            status = "Pass";
        }

        excelTestExecutor.runTests(stepName, status);
        Thread.sleep(2000);
    }

}
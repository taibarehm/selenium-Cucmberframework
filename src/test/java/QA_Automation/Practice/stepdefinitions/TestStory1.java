package QA_Automation.Practice.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

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
import QA_Automation.Practice.utils.JavascrUtility;

public class TestStory1 {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ExcelTestExecutor excelTestExecutor = new ExcelTestExecutor();
    private JavascrUtility jsUtil;
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
        jsUtil = new JavascrUtility(driver);
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
        String[] parts = input.split(",");
        String name = parts[0].trim();
        String email = parts[1].trim();
        System.out.println("Name: " + name + ", Email: " + email);
        System.out.println("Entered name and email");
        if (loginPage.enterNameAndEmail(name, email) && loginPage.clickSignup()) {
            status = "Pass";
        }
        excelTestExecutor.runTests(step, status);
    }

    @Then("{string} form is visible")
    public void form_is_visible(String expectedText) throws Exception {
        step = "Verify 'ENTER ACCOUNT INFORMATION' is visible";
        String status = "fail";

        // Assuming registrationPage has a method to check the header text
        boolean isVisible = registrationPage.getFormTitle().equalsIgnoreCase(expectedText);

        if (isVisible) {
            status = "Pass";
        }

        excelTestExecutor.runTests(step, status);
        assert isVisible : expectedText + " was not visible!";
    }

    @When("Fields are populated correctly")
    public void fields_are_populated_correctly() throws Exception {
        step = "Fill registration details";
        String status = "fail";
        excelTestExecutor.runTests(step, status);
    }
}
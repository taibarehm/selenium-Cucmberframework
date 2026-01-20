package QA_Automation.Practice.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascrUtility { // Ensure this matches the filename exactly

    private WebDriver driver;

    public JavascrUtility(WebDriver driver) {
        this.driver = driver;
    }

    public void highlightElement(WebElement element) {
        // We use the driver to create the executor
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Apply highlight
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 4px solid red;');", element);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Reset highlight
        js.executeScript("arguments[0].setAttribute('style', 'border: none; background: none;');", element);
    }
}
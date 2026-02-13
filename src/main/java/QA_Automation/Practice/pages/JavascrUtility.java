package QA_Automation.Practice.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascrUtility {

    private WebDriver driver;

    public JavascrUtility(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * Executes custom JavaScript on a specific element.
     * Fixes the 'red error' in your RegistrationPage.
     */
    public void executeJS(String script, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script, element);
    }

    /**
     * Removes Google Ads and iframes that commonly intercept clicks.
     */
    public void removeAds() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = "var iframes = document.getElementsByTagName('iframe');" +
                "for (var i = 0; i < iframes.length; i++) {" +
                "    iframes[i].style.display='none';" +
                "}";
        js.executeScript(script);
    }

    public void highlightElement(WebElement element) {
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
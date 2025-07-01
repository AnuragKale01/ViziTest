package AutomationTesting.Vizismart;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class DownloadFile extends BaseTest {

    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @Test
    public void login_and_download() {
        Reporter.log("Starting DownloadFile test", true);

        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Login
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kalyanideshmukh778+5@gmail.com");
        Reporter.log("Entered email", true);

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345678");
        Reporter.log("Entered password", true);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Reporter.log("Clicked on Login button", true);

        wait.until(ExpectedConditions.urlContains("/properties"));
        Reporter.log("Successfully navigated to Properties page", true);

        // Click SVG option icon
        WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("svg.scale-150.text-white")));
        svgIcon.click();
        Reporter.log("Clicked on Option Menu SVG icon", true);

        // Click Manage KBase
        WebElement manageKBase = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[text()='Manage KBase']")));
        manageKBase.click();
        Reporter.log("Clicked on 'Manage KBase'", true);

        // Click download link in the first row
        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("table tbody tr:first-child td:nth-child(4) a")));
        scrollTo(downloadLink);
        downloadLink.click();
        Reporter.log("Clicked download link in first row", true);

        // Optional: Add download verification logic if the browser or app provides feedback
        // Example: wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-complete")));
    }
}

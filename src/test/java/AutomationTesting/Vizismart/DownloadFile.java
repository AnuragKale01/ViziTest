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

    @Test
    public void login_and_download() throws InterruptedException {
        Reporter.log("Starting DownloadFile test", true);

        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);

        // Login
        // WebElement emailField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/input"));
        WebElement emailField = driver.findElement(By.cssSelector("input[type='email']"));
        emailField.sendKeys("kalyanideshmukh778+5@gmail.com");
        Reporter.log("Entered email", true);

        // WebElement password = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/div/input"));
        WebElement password = driver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("12345678");
        Reporter.log("Entered password", true);

        // WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        WebElement loginBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        loginBtn.click();
        Reporter.log("Clicked on Login button", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/properties"));
        Reporter.log("Landed on Properties page", true);

        // SVG icon for option menu
        // WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[name()='svg' and contains(@class,'scale-150') and contains(@class,'text-white')]")));
        WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("svg.scale-150.text-white")));
        svgIcon.click();
        Reporter.log("Clicked on Option Menu SVG icon", true);

        // WebElement manageKBase = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[1]/div/div[3]/div/div[2]")));
        WebElement manageKBase = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[text()='Manage KBase']"))); // Better than full path if text is unique
        manageKBase.click();
        Reporter.log("Clicked on Manage KBase button", true);

        // WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/div[3]/div/div/div/div[1]/table/tbody/tr[1]/td[4]/a")));
        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.cssSelector("table tbody tr:first-child td:nth-child(4) a")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadLink);
        downloadLink.click();
        Reporter.log("Clicked download link on first row", true);

        // Optional: wait for download completion indicator if available
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-success-alert")));
    }
}

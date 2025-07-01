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
        WebElement emailField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/input"));
        emailField.sendKeys("kalyanideshmukh778+5@gmail.com");
        Reporter.log("Entered email", true);

        WebElement password = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/div/input"));
        password.sendKeys("12345678");
        Reporter.log("Entered password", true);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']"));
        loginBtn.click();
        Reporter.log("Clicked on Login button", true);

        // Wait until redirected to the properties page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("/properties"));
        Reporter.log("Landed on Properties page", true);

        // Open the option menu (SVG icon)
        WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[name()='svg' and contains(@class,'scale-150') and contains(@class,'text-white')]")
        ));
        svgIcon.click();
        Reporter.log("Clicked on Option Menu SVG icon", true);

        // Click "Manage KBase" (adjust XPath if necessary)
        WebElement manageKBase = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='root']/div/div[1]/div/div[3]/div/div[2]")
        ));
        manageKBase.click();
        Reporter.log("Clicked on Manage KBase button", true);

        // Wait for table row and click the download link in the first row
        WebElement downloadLink = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='root']/div/div[3]/div/div/div/div[1]/table/tbody/tr[1]/td[4]/a")
        ));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", downloadLink);
        downloadLink.click();
        Reporter.log("Clicked download link on first row", true);

        // Optionally, wait until download completes or some success indicator appears
        // e.g., wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("download-success-alert")));

        // Let the BaseTest @AfterMethod handle driver.quit()
    }
}

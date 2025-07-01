package AutomationTesting.Vizismart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class Add_multifamily_Property extends BaseTest {

    private void scrollAndClick(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            element.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private void scrollAndSendKeys(WebElement element, String text) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.sendKeys(text);
    }

    @Test
    public void add() {
        Reporter.log("Starting Add_multifamily_Property test", true);

        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Login
        driver.findElement(By.cssSelector("input[type='email']")).sendKeys("kalyanideshmukh778+11@gmail.com");
        Reporter.log("Entered email address", true);

        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("12345678");
        Reporter.log("Entered password", true);

        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Reporter.log("Clicked on Login button", true);

        // Click on Property icon
        WebElement propertyIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("Properties6")));
        scrollAndClick(propertyIcon);
        Reporter.log("Clicked on Property icon", true);

        // MultiFamily radio button
        WebElement multiFamily = wait.until(ExpectedConditions.elementToBeClickable(By.id("Multi Family")));
        scrollAndClick(multiFamily);
        Reporter.log("Clicked on Multi-Family property", true);

        // Address fields
        scrollAndSendKeys(driver.findElement(By.xpath("//form//input[1]")), "Five Garden plaza");
        Reporter.log("Entered address", true);

        scrollAndSendKeys(driver.findElement(By.xpath("//form//input[2]")), "Pune");
        Reporter.log("Entered city", true);

        scrollAndSendKeys(driver.findElement(By.xpath("//form//input[3]")), "Maharashtra");
        Reporter.log("Entered state", true);

        scrollAndSendKeys(driver.findElement(By.xpath("//form//input[4]")), "411057");
        Reporter.log("Entered zip code", true);

        // Add units
        WebElement unit1 = driver.findElement(By.id("add14"));
        unit1.sendKeys("1");

        WebElement addUnitsBtn = driver.findElement(By.id("add16"));
        addUnitsBtn.click();

        WebElement unit2 = driver.findElement(By.xpath("//input[@placeholder='Unit #' and @value='']"));
        unit2.sendKeys("2");
        Reporter.log("Added 2 units", true);

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add23")));
        scrollAndClick(saveBtn);
        Reporter.log("Clicked on Save button", true);

        // Wait for confirmation or redirection if needed
        wait.until(ExpectedConditions.invisibilityOf(saveBtn));
        Reporter.log("Save process completed", true);
    }
}

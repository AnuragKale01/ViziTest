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

    @Test
    public void add() throws InterruptedException {
        Reporter.log("Starting Add_multifamily_Property test", true);

        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);

        // Login
        WebElement emailField = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/input")); // ✅ RECOMMENDED: Use CSS selector like input[type='email']
        emailField.sendKeys("kalyanideshmukh778+11@gmail.com");
        Reporter.log("Entered email address", true);

        WebElement password = driver.findElement(By.xpath("//*[@id='root']/div/div[1]/div/div/form/div/input")); // ✅ RECOMMENDED: Use input[type='password'] as CSS selector
        password.sendKeys("12345678");
        Reporter.log("Entered password", true);

        WebElement loginBtn = driver.findElement(By.xpath("//button[@type='submit']")); // ✅ RECOMMENDED: CSS selector also works: button[type='submit']
        loginBtn.click();
        Reporter.log("Clicked on Login button", true);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click on Property icon
        WebElement propertyIcon = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='Properties6']"))); // ✅ RECOMMENDED: Use By.id("Properties6") directly
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", propertyIcon);
        propertyIcon.click();
        Reporter.log("Clicked on Property icon", true);

        // MultiFamily radio button
        WebElement multiFamily = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id=\"Multi Family\"]"))); // ✅ RECOMMENDED: Use relative XPath or label-based XPath
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", multiFamily);
        try {
            multiFamily.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", multiFamily);
        }
        Reporter.log("Clicked on Multi-Family property", true);

        // Address fields
        WebElement address = driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div/div/form/div[1]/input[1]")); // ✅ RECOMMENDED: Use placeholder or name attribute as CSS selector
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", address);
        address.sendKeys("Five Garden plaza");
        Reporter.log("Entered address", true);

        WebElement city = driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div/div/form/div[1]/input[2]")); // ✅ RECOMMENDED: Same as above
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", city);
        city.sendKeys("Pune");
        Reporter.log("Entered city", true);

        WebElement state = driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div/div/form/div[1]/input[3]")); // ✅ RECOMMENDED: Same as above
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", state);
        state.sendKeys("Maharashtra");
        Reporter.log("Entered state", true);

        WebElement zip = driver.findElement(By.xpath("//*[@id='root']/div/div[3]/div/div/form/div[1]/input[4]")); // ✅ RECOMMENDED: Same as above
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", zip);
        zip.sendKeys("411057");
        Reporter.log("Entered zip code", true);

        // Add units
        WebElement unit1 = driver.findElement(By.xpath("//*[@id='add14']")); // ✅ RECOMMENDED: Use By.id("add14")
        unit1.sendKeys("1");

        WebElement addUnitsBtn = driver.findElement(By.xpath("//*[@id='add16']")); // ✅ RECOMMENDED: Use By.id("add16")
        addUnitsBtn.click();

        WebElement unit2 = driver.findElement(
            By.xpath("/html/body/div/div/div[3]/div/div/form/div[1]/div[2]/div[2]/input")); // ✅ RECOMMENDED: Use placeholder='Unit #' or class if available
        unit2.sendKeys("2");
        Reporter.log("Added 2 units", true);

        // Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//*[@id='add23']"))); // ✅ RECOMMENDED: Use By.id("add23")
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
        saveBtn.click();
        Reporter.log("Clicked on Save button", true);

        // Final pause
        Thread.sleep(2000); // ✅ Consider replacing with explicit wait
    }
}

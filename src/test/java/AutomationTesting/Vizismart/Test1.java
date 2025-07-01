package AutomationTesting.Vizismart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.time.Duration;

public class Test1 extends BaseTest {

    @Test
    public void login_page() {
        Reporter.log("ChromeDriver initialized with unique profile", true);

        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get("https://dev.vizismart.com/login");
            Reporter.log("Navigated to Login page", true);

            driver.manage().window().maximize();

            WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/input"));
            emailField.sendKeys("kalyanideshmukh778+5@gmail.com");
            Reporter.log("Entered Email", true);

            WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/div/input"));
            password.sendKeys("12345678");
            Reporter.log("Entered Password", true);

            WebElement loginButton = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
            loginButton.click();
            Reporter.log("Clicked on Login button", true);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlContains("/properties"));
            Reporter.log("Login successful, navigated to Properties page", true);

            // Wait for SVG icon to be clickable and click it
            WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[name()='svg' and contains(@class, 'scale-150') and contains(@class, 'text-white')]")));
            svgIcon.click();
            Reporter.log("Clicked on SVG Icon", true);

        } catch (Exception e) {
            Reporter.log("Test failed: " + e.getMessage(), true);
            throw new RuntimeException("Login Test Failed", e);
        }
    }
}

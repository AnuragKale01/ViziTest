package AutomationTesting.Vizismart;

import org.testng.annotations.Test;

import java.time.Duration;

import org.openqa.selenium.By;      
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 extends BaseTest {

    @Test
    public void login_page() throws InterruptedException {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Reporter.log("ChromeDriver initialized", true);

        // Navigate to the login page
        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);

        // Maximize the browser window
        driver.manage().window().maximize();

        // Locate the email field and enter the email
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/input"));
        emailField.sendKeys("kalyanideshmukh778+5@gmail.com");
        Reporter.log("Entered Email", true);

        // Locate the password field and enter the password
        WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/div/input"));
        password.sendKeys("12345678");
        Reporter.log("Entered Password", true);

        // Click the login button
        WebElement Login = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        Login.click();
        Reporter.log("Clicked on Login button", true);

        // Wait for 2 seconds
        Thread.sleep(2000);
        Reporter.log("Waited for 2 seconds", true);

        // Close the browser
        
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//      
		wait.until(ExpectedConditions.urlContains("https://dev.vizismart.com/properties"));

//      
		WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[name()='svg' and contains(@class, 'scale-150') and contains(@class, 'text-white')]")));
		svgIcon.click();
        driver.close();
        Reporter.log("Closed the browser", true);
    }
}

package AutomationTesting.Vizismart;

import org.testng.annotations.Test;

import org.openqa.selenium.By;      
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class AutoLogin extends BaseTest {
	@Test
    public void login_page() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        Reporter.log("ChromeDriver initialized", true);
 
        driver.get("https://dev.vizismart.com/login");
        Reporter.log("Navigated to Login page", true);
 
        
        driver.manage().window().maximize();
 
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/input"));
        emailField.sendKeys("kalyanideshmukh778+5@gmail.com");
        Reporter.log("Entered Email", true);
 
        WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/div/input"));
        password.sendKeys("12345678");
        Reporter.log("Entered Password", true);
        
        WebElement Login = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
        Login.click();
        Reporter.log("Clicked on Login button", true);
 
        Thread.sleep(2000);
        Reporter.log("Waited for 2 seconds", true);
 
        driver.close();
//        Reporter.log("Closed the browser", true);
    }
}

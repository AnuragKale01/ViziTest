package AutomationTesting.Vizismart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BulkPropert {
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
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
//        
		wait.until(ExpectedConditions.urlContains("https://dev.vizismart.com/properties"));

//        
		WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"Properties6\"]")));
		svgIcon.click();

		Thread.sleep(5000);

		Reporter.log("Clicked on Property Menu", true);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");

		Thread.sleep(2000);
		
		WebElement BulkButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add25\"]")));
		BulkButton.click();
		Thread.sleep(5000);
		Reporter.log("Bulk Button Clicked", true);
		Thread.sleep(2000);
		
		

		WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#bulk5")));
		fileInput.sendKeys("C:\\Users\\ASUS\\Downloads\\Bulk Upload - Template 2.csv");
		Reporter.log("File Selected ", true);

		Thread.sleep(7000);

		
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"bulk12\"]"))).click();
		Reporter.log("Upload Button Clicked", true);

		driver.quit();
		Reporter.log("Closed the browser", true);
	}
}

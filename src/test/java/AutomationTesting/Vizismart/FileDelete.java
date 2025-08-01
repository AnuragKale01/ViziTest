package AutomationTesting.Vizismart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FileDelete {
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
		WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[name()='svg' and contains(@class, 'scale-150') and contains(@class, 'text-white')]")));
		svgIcon.click();

		Thread.sleep(5000);

		Reporter.log("Clicked on Option Menu", true);

		WebElement ManageKBaseButton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[3]/div/div[2]")));
		ManageKBaseButton.click();
		Thread.sleep(5000);
		Reporter.log("Option Menu Clicked", true);
		
		
		//Checkbox 1
		WebElement CheckFile=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/table/tbody/tr[3]/td[1]/input")));
		CheckFile.click();
		
		// CHeckBox 2
		WebElement CheckFile1=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[1]/table/tbody/tr[2]/td[1]/input")));
		CheckFile1.click();
		
		
		Thread.sleep(2000);
		Reporter.log("CheckBox Checked",true);
		
		WebElement DeleteFile = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[3]/div/div/div/div[2]/button"));
		DeleteFile.click();
		Thread.sleep(7000);
		// Click the "Yes, delete it" confirmation button
		WebElement confirmButton = driver.findElement(By.cssSelector("button.swal2-confirm"));
		confirmButton.click();
		Thread.sleep(5000);
		Reporter.log("Alert Box Open And Click on option", true);
		
		driver.quit();
		Reporter.log("Closed the browser", true);
	}
}

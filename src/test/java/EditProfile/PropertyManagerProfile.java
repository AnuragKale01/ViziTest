package EditProfile;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PropertyManagerProfile {
	ChromeDriver driver = new ChromeDriver();

	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	@Test
	public void login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		Reporter.log("ChromeDriver initialized", true);

		driver.get("https://dev.vizismart.com/login");
		Reporter.log("Navigated to Login page", true);

		driver.manage().window().maximize();

		WebElement emailField = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/input"));
		emailField.sendKeys("kalyanideshmukh778+313@gmail.com");
		Reporter.log("Entered Email", true);

		WebElement password = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div/form/div/input"));
		password.sendKeys("12345678");
		Reporter.log("Entered Password", true);

		WebElement Login = driver.findElement(By.xpath("//button[@type=\"submit\"]"));
		Login.click();
		Reporter.log("Clicked on Login button", true);

		Thread.sleep(2000);
		Reporter.log("Waited for 2 seconds", true);
	}

	@Test(dependsOnMethods = { "login" })
	public void HumburgerMenu() throws InterruptedException {

		wait.until(ExpectedConditions.urlContains("https://dev.vizismart.com/properties"));

//      
		WebElement svgIcon = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//*[name()='svg' and contains(@class, 'scale-150') and contains(@class, 'text-white')]")));
		svgIcon.click();

		Thread.sleep(5000);

		Reporter.log("Clicked on Humburger Menu", true);

//		Reporter.log("Clicked on Option Menu", true);

		WebElement Profilebutton = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//*[@id=\"root\"]/div/div[1]/div/div[3]/div/div[3]")));
		Profilebutton.click();
		Thread.sleep(5000);
		Reporter.log("Profile Option Menu Clicked", true);

	}

	@Test(dependsOnMethods = { "HumburgerMenu" })
	public void EditProfile() throws InterruptedException {
		wait.until(ExpectedConditions.urlContains("https://dev.vizismart.com/profile"));

		WebElement EditIcon = driver.findElement(By.xpath("//*[@id=\"profile4\"]"));
		EditIcon.click();

		WebElement name = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"profile15\"]/input")));
		name.click();
		Thread.sleep(1000);

		// Simulate CTRL+A and BACKSPACE
		name.sendKeys(Keys.CONTROL + "a");
		name.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(1000);

		// Type new value
		name.sendKeys("Anurag");

		Reporter.log("name", true);

//		WebElement Role=driver.findElement(By.xpath("//*[@id=\"profile18\"]/input"));

		WebElement Role = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"profile18\"]/input")));
		Role.click();
//		Thread.sleep(2000);
		Role.sendKeys(Keys.CONTROL + "a");
		Role.sendKeys(Keys.BACK_SPACE);
//		Thread.sleep(2000);
		Role.sendKeys("Jr.Tester");

		Reporter.log("Role Has entered", true);
//		Thread.sleep(2000);
		// *[@id="profile21"]/input

////		WebElement Company=driver.findElement(By.xpath("//*[@id=\"profile21\"]/input"));

		WebElement Company = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"profile21\"]/input")));
		Company.click();
//		Thread.sleep(2000);
		Company.sendKeys(Keys.CONTROL + "a");
		Company.sendKeys(Keys.BACK_SPACE);
//		Thread.sleep(2000);
		Company.sendKeys("64");

		Reporter.log("Company Has entered", true);
//		Thread.sleep(2000);

////		//*[@id="profile36"]/input[1]

////		WebElement PhoneNumber=driver.findElement(By.xpath("//*[@id=\"profile36\"]/input[1]"));
		WebElement PhoneNumber = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"profile36\"]/input[1]")));
		PhoneNumber.sendKeys(Keys.CONTROL + "a");
		PhoneNumber.sendKeys(Keys.BACK_SPACE);

		PhoneNumber.sendKeys("777-000-9945");

		Reporter.log("PhoneNumber Has entered", true);
		Thread.sleep(2000);

////		For Sms Check Box

		WebElement SmsCheckBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name=\"SMS\"]")));
		SmsCheckBox.click();
		Reporter.log("Unchecked Sms Checkbox", true);
		Thread.sleep(2000);

//		//for email

////		WebElement EmailCheckBox=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@name=\"Email\"]")));
////		EmailCheckBox.click();
////		Reporter.log("Unchecked Email Checkbox",true);
////		Thread.sleep(2000);

		WebElement DoneButton = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"notification10\"]")));
		DoneButton.click();
		Reporter.log("Done Button Clicked", true);
		Thread.sleep(2000);
		driver.quit();

	}

}

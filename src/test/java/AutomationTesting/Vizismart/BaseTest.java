package AutomationTesting.Vizismart;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
	@BeforeClass
    public void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();

        // Create a unique temporary directory for user-data
        Path tempProfile = Files.createTempDirectory("chrome-user-data");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());

        // Other options if needed
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // if you're on a CI server

        driver = new ChromeDriver(options);
    }

    @AfterMethod
	@AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

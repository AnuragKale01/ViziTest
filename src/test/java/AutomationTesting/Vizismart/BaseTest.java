package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    private Path tempProfile; // to clean up if needed

    @BeforeClass
    public void beforeClass() {
        // Global setup for all tests in the class
        System.out.println("BeforeClass: Starting test class setup.");
    }

    @BeforeMethod
    public void setUp() throws IOException {
        // Ensure the correct driver binary is available
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Create a unique temporary directory for user-data to avoid conflicts
        tempProfile = Files.createTempDirectory("chrome-user-data");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());

        // CI and stability options
        options.addArguments("--headless=new");              // headless mode
        options.addArguments("--no-sandbox");                // required in CI
        options.addArguments("--disable-dev-shm-usage");     // reduce resource usage
        options.addArguments("--disable-gpu");               // recommended for headless
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("BeforeMethod: Driver initialized.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("AfterMethod: Driver quit.");
        }
    }

    @AfterClass
    public void afterClass() {
        // Clean up the temporary profile directory
        if (tempProfile != null && Files.exists(tempProfile)) {
            try {
                Files.walk(tempProfile)
                     .sorted((a, b) -> b.compareTo(a))
                     .forEach(path -> {
                         try {
                             Files.delete(path);
                         } catch (IOException e) {
                             System.err.println("Failed to delete: " + path);
                         }
                     });
                System.out.println("AfterClass: Temporary profile deleted.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

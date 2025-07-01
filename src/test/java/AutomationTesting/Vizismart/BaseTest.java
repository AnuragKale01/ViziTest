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
    private Path tempProfileDir; // used for cleanup if needed

    @BeforeClass
    public void beforeClass() {
        // Class-level setup if required
        System.out.println("[BaseTest] BeforeClass: Test suite setup starting...");
    }

    @BeforeMethod
    public void setUp() {
        try {
            // Download and configure ChromeDriver
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            // CI-friendly flags
            options.addArguments("--headless=new");          // run in headless mode
            options.addArguments("--no-sandbox");            // disable sandbox for Linux CI
            options.addArguments("--disable-dev-shm-usage"); // overcome limited /dev/shm
            options.addArguments("--disable-gpu");           // applicable in some environments
            options.addArguments("--incognito");             // use incognito to avoid profile locks
            options.addArguments("--remote-allow-origins=*");

            // ✅ Create unique temp directory for user data
            tempProfileDir = Files.createTempDirectory("chrome-profile");
            options.addArguments("--user-data-dir=" + tempProfileDir.toString());

            // Initialize driver
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("[BaseTest] BeforeMethod: ChromeDriver initialized in headless mode.");
        } catch (IOException e) {
            System.err.println("[BaseTest] Error creating temporary profile directory: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[BaseTest] Error during Chrome setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("[BaseTest] AfterMethod: ChromeDriver session ended.");
        }

        // Optional: Cleanup temporary profile directory
        if (tempProfileDir != null) {
            try {
                Files.walk(tempProfileDir)
                        .sorted((a, b) -> b.compareTo(a)) // delete children before parents
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                System.err.println("[BaseTest] Failed to delete temp file: " + path);
                            }
                        });
                System.out.println("[BaseTest] AfterMethod: Temp profile directory cleaned up.");
            } catch (IOException e) {
                System.err.println("[BaseTest] Error cleaning up temp profile directory: " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        // Class-level teardown if required
        System.out.println("[BaseTest] AfterClass: Test suite teardown complete.");
    }
}

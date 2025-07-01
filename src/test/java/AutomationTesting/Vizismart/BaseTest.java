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

/**
 * BaseTest provides setup and teardown methods for WebDriver-based tests.
 * ChromeDriver runs in headless mode with CI-friendly options.
 */
public class BaseTest {

    protected WebDriver driver;
    private Path tempProfileDir;

    /**
     * Executed once before any test methods in the class.
     */
    @BeforeClass
    public void beforeClass() {
        System.out.println("[BaseTest] BeforeClass: Initializing test suite...");
    }

    /**
     * Executed before each test method. Sets up a headless ChromeDriver with a temp profile.
     */
    @BeforeMethod
    public void setUp() {
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--incognito");
            options.addArguments("--remote-allow-origins=*");

            // Create isolated user profile directory
            tempProfileDir = Files.createTempDirectory("chrome-profile");
            options.addArguments("--user-data-dir=" + tempProfileDir);

            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            System.out.println("[BaseTest] BeforeMethod: ChromeDriver initialized with temp profile.");
        } catch (IOException e) {
            System.err.println("[BaseTest] Failed to create temp profile directory: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("[BaseTest] Error during WebDriver setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Executed after each test method. Quits the driver and cleans up the temp profile directory.
     */
    @AfterMethod
    public void tearDown() {
        // Quit WebDriver
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("[BaseTest] AfterMethod: ChromeDriver closed.");
            } catch (Exception e) {
                System.err.println("[BaseTest] Failed to quit driver: " + e.getMessage());
            }
        }

        // Delete temporary profile directory
        if (tempProfileDir != null) {
            try {
                Files.walk(tempProfileDir)
                        .sorted((a, b) -> b.compareTo(a)) // Delete children before parents
                        .forEach(path -> {
                            try {
                                Files.deleteIfExists(path);
                            } catch (IOException e) {
                                System.err.println("[BaseTest] Could not delete: " + path + " - " + e.getMessage());
                            }
                        });
                System.out.println("[BaseTest] AfterMethod: Temporary profile directory cleaned.");
            } catch (IOException e) {
                System.err.println("[BaseTest] Error during temp profile cleanup: " + e.getMessage());
            }
        }
    }

    /**
     * Executed once after all test methods in the class have run.
     */
    @AfterClass
    public void afterClass() {
        System.out.println("[BaseTest] AfterClass: Test suite completed.");
    }
}

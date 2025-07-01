package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.util.UUID;

public class BaseTest {

    protected WebDriver driver;
    private Path tempProfileDir;

    @BeforeClass
    public void beforeClass() {
        System.out.println("[BaseTest] BeforeClass: Initializing test suite...");
    }

    @BeforeMethod
    public void setUp() {
        int maxRetries = 3;
        int attempt = 0;

        while (attempt < maxRetries) {
            try {
                WebDriverManager.chromedriver().setup();

                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--incognito");
                options.addArguments("--remote-allow-origins=*");

                // Create unique temp user profile
                tempProfileDir = Files.createTempDirectory("chrome-profile-" + UUID.randomUUID());
                options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath());

                driver = new ChromeDriver(options);
                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                System.out.println("[BaseTest] ChromeDriver initialized with profile: " + tempProfileDir);
                break;

            } catch (Exception e) {
                System.err.println("[BaseTest] Attempt " + (attempt + 1) + " failed: " + e.getMessage());
                e.printStackTrace();

                cleanupTempProfile(); // Clean up in case partially created

                attempt++;
                if (attempt < maxRetries) {
                    try {
                        Thread.sleep(2000); // Wait before retry
                    } catch (InterruptedException ignored) {}
                } else {
                    throw new RuntimeException("Failed to initialize WebDriver after " + maxRetries + " attempts.", e);
                }
            }
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
                System.out.println("[BaseTest] ChromeDriver closed.");
            } catch (Exception e) {
                System.err.println("[BaseTest] Failed to quit driver: " + e.getMessage());
            }
        }

        cleanupTempProfile();
    }

    private void cleanupTempProfile() {
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
                System.out.println("[BaseTest] Temporary profile directory cleaned: " + tempProfileDir);
            } catch (IOException e) {
                System.err.println("[BaseTest] Error during temp profile cleanup: " + e.getMessage());
            }
        }
    }

    @AfterClass
    public void afterClass() {
        System.out.println("[BaseTest] AfterClass: Test suite completed.");
    }
}

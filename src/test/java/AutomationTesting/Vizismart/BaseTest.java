package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class BaseTest {
    protected WebDriver driver;
    private Path tempProfileDir;

    @BeforeMethod
    public void setupDriver() throws Exception {
        WebDriverManager.chromedriver().setup(); // Ensure driver setup via WebDriverManager

        ChromeOptions options = new ChromeOptions();

        // Useful for CI/CD or headless execution
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // Unique profile to avoid session clashes
        tempProfileDir = Files.createTempDirectory("chrome-profile-" + UUID.randomUUID());
        options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath().toString());

        driver = new ChromeDriver(options);
        System.out.println("ChromeDriver started with unique profile: " + tempProfileDir);
    }

    @AfterMethod
    public void teardownDriver() {
        if (driver != null) {
            driver.quit();
        }

        if (tempProfileDir != null && Files.exists(tempProfileDir)) {
            deleteDirectory(tempProfileDir.toFile());
            System.out.println("Deleted temp profile directory: " + tempProfileDir);
        }
    }

    private void deleteDirectory(java.io.File file) {
        if (file.isDirectory()) {
            for (java.io.File child : file.listFiles()) {
                deleteDirectory(child);
            }
        }
        file.delete();
    }
}

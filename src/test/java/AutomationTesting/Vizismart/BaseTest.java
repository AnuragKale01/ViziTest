package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class BaseTest {
    protected WebDriver driver;
    private Path tempProfileDir;

    public void setupDriver() throws Exception {
        ChromeOptions options = new ChromeOptions();

        // REQUIRED for CI/CD environments
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        // REQUIRED: unique user-data-dir to prevent session conflicts
        tempProfileDir = Files.createTempDirectory("chrome-profile-" + UUID.randomUUID());
        options.addArguments("--user-data-dir=" + tempProfileDir.toAbsolutePath().toString());

        driver = new ChromeDriver(options);
    }

    public void teardownDriver() {
        if (driver != null) {
            driver.quit();
        }

        // Cleanup temp user data directory
        if (tempProfileDir != null && Files.exists(tempProfileDir)) {
            deleteDirectory(tempProfileDir.toFile());
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

package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BaseTest {

    protected WebDriver driver;
    private Path tempProfile; // to clean up if needed

    @BeforeClass
    public void beforeClass() {
        // You can add class-level setup here if needed
        System.out.println("BeforeClass: Starting test class setup.");
    }

    @BeforeMethod
    public void setUp() throws IOException {
        ChromeOptions options = new ChromeOptions();

        // Create a unique temporary directory for user-data
        tempProfile = Files.createTempDirectory("chrome-user-data");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath().toString());

        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless"); // comment this if you want to see browser UI

        driver = new ChromeDriver(options);
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
        // Optionally delete the temporary profile
        if (tempProfile != null && Files.exists(tempProfile)) {
            try {
                Files.walk(tempProfile)
                     .sorted((a, b) -> b.compareTo(a)) // delete files before dirs
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

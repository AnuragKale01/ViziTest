package AutomationTesting.Vizismart;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        // Class-level setup if required
        System.out.println("[BaseTest] BeforeClass: Test suite setup starting...");
    }

    @BeforeMethod
    public void setUp() {
        // Download and configure ChromeDriver
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        // CI-friendly flags
        options.addArguments("--headless=new");          // run in headless mode
        options.addArguments("--no-sandbox");           // disable sandbox for Linux CI
        options.addArguments("--disable-dev-shm-usage"); // overcome limited /dev/shm
        options.addArguments("--disable-gpu");          // applicable in some environments
        options.addArguments("--incognito");            // use incognito to avoid profile locks
        options.addArguments("--remote-allow-origins=*");

        // Initialize driver with options
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        System.out.println("[BaseTest] BeforeMethod: ChromeDriver initialized in headless mode.");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("[BaseTest] AfterMethod: ChromeDriver session ended.");
        }
    }

    @AfterClass
    public void afterClass() {
        // Class-level teardown if required
        System.out.println("[BaseTest] AfterClass: Test suite teardown complete.");
    }
}

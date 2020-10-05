import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ReportTest {

    public static ExtentReports extent;
    public static ExtentTest test;

    public static WebDriver driver;

    @BeforeClass

    public static void runOnceBeforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\galit\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://translate.google.co.il");

        ExtentSparkReporter htmlReporter = new ExtentSparkReporter("C:\\Users\\galit\\Desktop\\extent.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        test = extent.createTest("GoogleReporter", "Report");
        extent.setSystemInfo("Environment", "Galit-Automation");
        extent.setSystemInfo("Student", "Galit");
        test.log(Status.INFO, "@Before class");
    }

    @Test
    public void test01_openGoogleTranslateAndTakeScreenShot(){
        driver.get("https://translate.google.co.il");
        test.log(Status.PASS, "open google translate and take a screen shot take a screen shot", MediaEntityBuilder.createScreenCaptureFromPath(takeScreenShot("C:\\Users\\galit\\Desktop")).build());
    }

    @Test
    public void test02_pressOnTranslationField(){
        driver.findElement(By.id("source")).click();
        test.log(Status.PASS, "press on translation field");

    }

    private static String takeScreenShot(String ImagePath){
        TakesScreenshot takesScreenshot = (TakesScreenshot)driver;
        File file = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(ImagePath+".jpg");
        try {
            FileUtils.copyFile(file, destinationFile);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return ImagePath+".jpg";
    }



    @AfterClass
    public static void afterClass(){
        extent.flush();
        driver.quit();
    }

}

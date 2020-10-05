import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class NavigationTest {

    public static WebDriver driver;

    private String googleWindow = "";
    private String youTubeWindow = "https://youtube.com";



    @BeforeClass

    public static void runOnceBeforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\galit\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://google.com");
    }

    @Test
    public void test01_openGoogleWindow(){
        driver.switchTo().window(googleWindow);

    }

    @Test
    public void test02_openYouTubeWindow(){
        ((JavascriptExecutor)driver).executeScript("window.open('https://youtube.com','_blank');");

    }


    @Test
    public void test03_openGoogleTranslateWindow(){
        ((JavascriptExecutor)driver).executeScript("window.open('https://translate.google.com','_blank');");

    }


    @Test
    public void test04_navigateToGoogleWindow(){
        driver.switchTo().window(googleWindow);

    }

    @Test
    public void test05_navigateToYouTubeWindow(){
        Set<String> handle = driver.getWindowHandles();
        Iterator<String>iterator = handle.iterator();
        while (iterator.hasNext()){
            youTubeWindow = iterator.next();
            if(!googleWindow.equals(youTubeWindow));
            driver.switchTo().window(youTubeWindow);
        }
    }
    
}

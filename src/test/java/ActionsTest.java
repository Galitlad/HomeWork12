import org.apache.maven.surefire.shared.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import com.example.JSUtils;



public class ActionsTest {

    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\galit\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://dgotlieb.github.io/Actions");
    }


    @Test
    public void test01_screenShotBoxElement() {
        elementScreenShot(driver.findElement(By.id("div1")));

    }

    private static void elementScreenShot(WebElement element){
        File file = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("boxElement-screenshot.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Test

    public void test02_dragWheelIntoBox(){
        WebElement wheel = driver.findElement(By.id("drag1"));
        WebElement box = driver.findElement(By.id("div1"));
        JSUtils.JavascriptDragAndDrop(driver,wheel,box);
    }

    @Test
    public void test03_doubleClickText(){
        WebElement textElement = driver.findElement(By.xpath("//p[@ondblclick=\"doubleClickFunction()\"]"));
        Actions action = new Actions(driver);
        action.doubleClick(textElement);
        action.perform();
        String result = driver.findElement(By.id("demo")).getText();
        Assert.assertNotEquals(result,textElement.getText());
        System.out.println("The text before double click is : " +textElement.getText()+" the text after double click is : "+ result );
    }

    @Test
    public void test04_mouseHoverOnX(){
        Actions hoverXElement = new Actions(driver);
        WebElement element = driver.findElement(By.id("close"));
        hoverXElement.moveToElement(element).perform();
    }

    @Test
    public void test05_selectTwoItems(){
        Select select = new Select(driver.findElement(By.xpath("/html/body/form[1]/select")));
        Actions actions = new Actions(driver);
        actions.clickAndHold(select.getOptions().get(0)).clickAndHold(select.getOptions().get(2));
        actions.build().perform();
        System.out.println(select.getOptions().get(0).getText() +" and " + select.getOptions().get(1).getText());
    }

    @Test
    public void test06_uploadFile(){
        driver.findElement(By.name("pic")).sendKeys("C:\\Users\\galit\\Desktop\\new file\\picture.jpg");
    }

    @Test
    public void test07_scrollDownToElement(){
        WebElement element = driver.findElement(By.id("clickMe"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
        System.out.println(element.getText());
    }


    @Test
    public void test08_scrollToLocation(){
        WebElement element = driver.findElement(By.id("clickMe"));
        System.out.println("X = " + element.getRect().getX() +" AND Y = " + element.getRect().getY());
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("javascript:window.scrollBy(8,891)");
        System.out.println(element.getText());
    }

}

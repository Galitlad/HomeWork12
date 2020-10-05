import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XMLTest {

    private static WebDriver driver;

    @BeforeClass
    public static void beforeClass() throws Exception {
        String type = xmlInfo("URL");
        String browser = xmlInfo("browserType");
        if (browser.equals("Chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\galit\\Downloads\\chromedriver_win32 (2)\\chromedriver.exe");
            driver = new ChromeDriver();
        }
    }


    @Test
    public void openURL() throws Exception{
        driver.get(xmlInfo("URL"));
    }

    @Test
    public void openBrowser() throws Exception{
        driver.get("https://www.ynet.co.il");
    }


    private static String xmlInfo (String keyName) throws Exception{

        File fXmlFile = new File("C:\\Users\\galit\\Desktop\\new file\\info.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(keyName).item(0).getTextContent();
    }






}

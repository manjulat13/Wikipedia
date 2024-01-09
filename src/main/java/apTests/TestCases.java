
package apTests;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
//Selenium Imports
import java.util.logging.Level;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.BrowserType;
///
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestCases {
    RemoteWebDriver driver;

    public TestCases() throws MalformedURLException {
        System.out.println("Constructor: TestCases");

        final DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setBrowserName(BrowserType.CHROME);
        driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);


        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

    }

    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public void testCase01() {
        System.out.println("Start Test case: testCase01");
        driver.get("https://www.wikipedia.org/");
        String Url=driver.getCurrentUrl();
        if(Url.contains("wikipedia"))
        {
            System.out.println("URL Containes wikipedia");
          
        }
        
        System.out.println("end Test case: testCase01");
    }
    public void testCase02() throws InterruptedException{
        driver.get("https://www.wikipedia.org/");
        Thread.sleep(3000);
        WebElement HeaderW=driver.findElement(By.xpath("//*[@id='www-wikipedia-org']/div[1]/h1/span"));
        String Header=HeaderW.getText();
        if(Header.contains("Wikipedia")){
            System.out.println("TestCase02 Containes Header Wikipedia");
        }
        List<WebElement> footer=driver.findElements(By.xpath("//*[@id='www-wikipedia-org']/p/small"));
        int count=footer.size();
        //System.out.println(count);
        for(int i=0;i<count;i++)
        {
            String footrname=footer.get(i).getText();
            if(footrname.contains("Terms of Use")){
                
                System.out.println("Footer containes "+footrname+" link");
            }
            if(footrname.contains("Privacy Policy")){
                
                System.out.println("Footer containes "+footrname+" link");
            }
        }
    }
    public void testCase03() throws InterruptedException {
        System.out.println("Start Test case: testCase03");
        driver.get("https://www.wikipedia.org/");
       WebElement searchbar=driver.findElement(By.id("searchInput"));
       searchbar.sendKeys("apple");
       WebElement appleInc=driver.findElement(By.xpath("//h3[text()=' Inc.']"));
       appleInc.click();
       WebDriverWait wait=new WebDriverWait(driver, 3);
       wait.until(ExpectedConditions.urlContains("Apple_Inc."));


      List<WebElement> founders=driver.findElements(By.xpath("//*[@id='mw-content-text']/div[1]/table[1]/tbody/tr[9]/td/div"));

        for(int i=0;i<founders.size();i++)
        {
            if(founders.get(i).getText().contains("Steve Jobs")){
                System.out.println("Founder is listed");
            }
        }
    }
    public void testCase04() {
        System.out.println("Start Test case: testCase04");
        driver.get("https://www.wikipedia.org/");
        
        WebElement searchbar=driver.findElement(By.id("searchInput"));
       searchbar.sendKeys("microsoft");
       WebElement microsoft=driver.findElement(By.xpath("//*[@id='typeahead-suggestions']/div/a[1]/div[1]/h3/em"));
       microsoft.click();
       WebDriverWait wait=new WebDriverWait(driver, 3);
       wait.until(ExpectedConditions.urlContains("/Microsoft"));


      WebElement founders=driver.findElement(By.xpath("(//th[text()='Founders']/..//a[text()='Bill Gates'])[1]"));

       founders.getText().contains("Bill Gates");
                      System.out.println("Founder is listed");
                founders.click();
                wait.until(ExpectedConditions.urlContains("/Bill_Gates"));
                String bGurl=driver.getCurrentUrl();
                if(bGurl.contains("Bill_Gates")){
                    System.out.println("Url containes BillGates and Test case Pass");
                }
            
        
        System.out.println("end Test case: testCase04");
    }

    public void testCase05() {
        System.out.println("Start Test case: testCase05");
        driver.get("https://en.wikipedia.org/wiki/Main_Page");
        WebElement mainmenu=driver.findElement(By.id("vector-main-menu-dropdown-checkbox"));
        mainmenu.click();
        WebElement aboutWikipedia=driver.findElement(By.xpath("//*[@id='n-aboutsite']/a"));
        aboutWikipedia.click();
        WebDriverWait wait=new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.urlContains("About"));
        String url=driver.getCurrentUrl();
        if(url.contains("About")){
            System.out.println("Url contains About and Tets case Passed");
        }

        
        System.out.println("end Test case: testCase05");
    }

}


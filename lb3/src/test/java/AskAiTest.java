import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AskAiTest {

  private JavascriptExecutor js;

  private WebDriver[] drivers = new WebDriver[2];

  @BeforeEach
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    WebDriverManager.firefoxdriver().setup();
    drivers[0] = new FirefoxDriver();
    drivers[1] = new ChromeDriver();
    drivers[0].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    drivers[1].manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
  }


  @Test
  public void askAiTest() throws InterruptedException {
    for (WebDriver driver : drivers) {
      js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      driver.get("https://www.answers.com/");
      driver.manage().window().setSize(new Dimension(1485, 1217));

      // Принятие cookies
      WebElement cookiesButton = driver.findElement(By.id("onetrust-accept-btn-handler"));
      cookiesButton.click();
      Thread.sleep(2000);
      try{
        driver.switchTo().frame(2);
        driver.findElement(By.cssSelector(".Bz112c > path:nth-child(1)")).click();
        driver.switchTo().defaultContent();
      }catch(NoSuchFrameException  e){
        System.out.println("Iframe not found or already handled.");
      }
      Thread.sleep(2000);
      // Выбрать AI чат
      driver.findElement(By.xpath("//div[@id='root']/div/div[2]/main/section[2]/div/div/div")).click();

      WebElement input = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/main/div[2]/div/form/div/div/input"));
      input.click();
      input.sendKeys("what is the capital of France?");

      driver.findElement(By.xpath("//button[@type='submit']")).click();

      WebElement messenge =  driver.findElement(By.xpath("//div[@id='root']/div/div[2]/main/div[2]/div/div[2]/div[2]/div/div/div/p"));
      assertEquals(messenge.getText().toString(), "what is the capital of France?", "Комментарий не был отправлен или не отображается корректно");
      driver.quit();
    }

  }
}

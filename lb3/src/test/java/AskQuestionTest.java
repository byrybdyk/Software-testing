import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AskQuestionTest {
  private JavascriptExecutor js;

  private WebDriver[] drivers = new WebDriver[2];

  @BeforeEach
  public void setUp() {
    WebDriverManager.chromedriver().setup();
    WebDriverManager.firefoxdriver().setup();
    drivers[0] = new FirefoxDriver();
    drivers[1] = new ChromeDriver();
  }

  @Test
  public void askAiQuestionTest() throws InterruptedException {
    for (WebDriver driver : drivers) {
      js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      driver.get("https://www.answers.com/");
      driver.manage().window().setSize(new Dimension(1485, 1217));

      //  Принятие cookies
      WebElement cookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("onetrust-accept-btn-handler")));
      cookiesButton.click();

      try {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(2));
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Bz112c > path:nth-child(1)")));
        closeBtn.click();
        driver.switchTo().defaultContent();
      } catch (NoSuchFrameException | TimeoutException e) {
        System.out.println("Iframe not found or already handled.");
        driver.switchTo().defaultContent();
      }
      Thread.sleep(2000);

      WebElement logInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div/div/div[4]/div[3]/span/button")));
      logInButton.click();

      Thread.sleep(2000);
      driver.findElement(By.xpath("//div[@id='root']/div/div[4]/div/div[2]/div/div[2]/div[2]/div/button[4]/span")).click();
      Thread.sleep(2000);
      driver.findElement(By.id("email-input")).sendKeys("byrybdyk@gmail.com");
      driver.findElement(By.id("outlined-adornment-password")).sendKeys("PSWD");


      driver.findElement(By.xpath("//button[@type='submit']")).click();


      Thread.sleep(2000);

      // Клик по первой кнопке (возможно, "Ask AI")
      WebElement firstButton = driver.findElement(By.xpath("//button[@type='button']"));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstButton);


      Thread.sleep(2000);
      WebElement askAiButton = driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[4]/div/div/div[2]/div/span/button/span"));
      askAiButton.click();
      Thread.sleep(2000);

      var questionText = "Who will win: Lirili Larila or Bobritto Bandito?";
      driver.findElement(By.xpath("//textarea[@type='text']")).click();
      driver.findElement(By.xpath("//textarea[@type='text']")).sendKeys(questionText);

      WebElement submitButton = driver.findElement(By.xpath("//div[@id='root']/div/div[4]/div/div[2]/div/div/button[2]"));
      new WebDriverWait(driver, Duration.ofSeconds(10))
              .until(ExpectedConditions.elementToBeClickable(submitButton));
      ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);

      Thread.sleep(2000);

      var headerText = "";
      try{
        WebElement header = driver.findElement(By.tagName("h1"));
        headerText = header.getText();
      }
      catch(Exception e){
        WebElement askAiButton2 = driver.findElement(By.xpath("//div[@id='root']/div/div[4]/div/div[2]/div/div[2]/div/div[2]/button/span"));
        askAiButton2.click();
        Thread.sleep(2000);
        WebElement header = driver.findElement(By.tagName("h1"));
        headerText = header.getText();
      }


      assertTrue(headerText.contains(questionText), "Заголовок страницы должен содержать текст вопроса");


      driver.close();
    }
  }

}

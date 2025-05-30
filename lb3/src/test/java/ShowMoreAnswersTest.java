import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ShowMoreAnswersTest {
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
  public void ShowMoreAnswersTest() throws InterruptedException {
    for (WebDriver driver : drivers) {
      driver.get("https://www.answers.com/natural-sciences/Selenium_API_testing_tool");
      driver.manage().window().setSize(new Dimension(1320, 973));
      js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

      driver.findElement(By.xpath("(//button[@type='button'])[2]")).click();

      WebElement answer = driver.findElement(By.xpath("//div[2]/div/div/div/div/p"));
      var answerText = answer.getText();
      assertNotEquals(answerText, "", "Комментарий не был отправлен или не отображается корректно");
      driver.quit();
    }

  }
}

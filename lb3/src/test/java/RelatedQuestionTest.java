import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RelatedQuestionTest {
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
  public void testParaguayPage() {
    for (WebDriver driver : drivers) {
      js = (JavascriptExecutor) driver;
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

      driver.get("https://www.answers.com/travel-destinations/How_many_states_and_Territories_does_Paraguay_have/");
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

      WebElement questionLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div[2]/div/div[5]/div[2]/a[2]/div/div/h3")));
      String questionText = questionLink.getText();

      questionLink.click();

      wait.until(ExpectedConditions.titleContains(questionText));

      WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
      String headerText = header.getText();

      assertTrue(headerText.contains(questionText), "Заголовок страницы должен содержать текст вопроса");

      driver.quit();
    }
  }
}

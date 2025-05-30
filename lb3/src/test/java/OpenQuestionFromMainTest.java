import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class OpenQuestionFromMainTest {
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
      driver.get("https://www.answers.com/");
      driver.manage().window().setSize(new Dimension(748, 687));
      js.executeScript("window.scrollTo(0,648)");
      js.executeScript("window.scrollTo(0,955)");

      WebElement questionLink = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/main/div[3]/div[4]/p/a"));
      String questionText = questionLink.getText();

      questionLink.click();

      new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.titleContains(questionText));

      WebElement header = driver.findElement(By.tagName("h1"));
      String headerText = header.getText();

      assertTrue(headerText.contains(questionText), "Заголовок страницы должен содержать текст вопроса");

      driver.quit();
    }
  }

}

// Converted to JUnit 5
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;


import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FindQuestionTest {
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
  public void searchSeleniumTest() throws InterruptedException {
    for (WebDriver driver : drivers) {
      js = (JavascriptExecutor) driver;
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

      driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[4]/span/img")).click();
      driver.findElement(By.xpath("//input[@type='search']")).click();
      driver.findElement(By.xpath("//input[@type='search']")).sendKeys("Selenium API testing tool");
      driver.findElement(By.xpath("//div[@id='root']/div/div[4]/aside/div/div[2]/div/form/img")).click();
      WebElement questionLink = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div/div[5]/div[2]/div[2]/div/a/strong[3]"));
      String questionText = questionLink.getText();
      questionLink.click();

      WebElement header = driver.findElement(By.tagName("h1"));
      String headerText = header.getText();
      assertTrue(headerText.contains(questionText), "Заголовок страницы должен содержать текст вопроса");
      driver.quit();
    }

    }
}

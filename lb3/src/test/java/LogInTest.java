import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogInTest {
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
  public void testLogIn() throws InterruptedException {
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
      // Открытие формы входа
      WebElement logInButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='root']/div/div/div/div[4]/div[3]/span/button")));
      logInButton.click();

      Thread.sleep(2000);

      driver.findElement(By.xpath("//div[@id='root']/div/div[4]/div/div[2]/div/div[2]/div[2]/div/button[4]/span")).click();

      driver.findElement(By.id("email-input")).sendKeys("byrybdyk@gmail.com");
      driver.findElement(By.id("outlined-adornment-password")).sendKeys("PSWD");

      driver.findElement(By.xpath("//button[@type='submit']")).click();
      driver.findElement(By.xpath("//img[@name='user-avatar']")).click();

      // Клик по кнопке перехода в профиль
      driver.findElement(By.xpath("//div[@id='root']/div/div/div/div[4]/div[3]/span/div/div/div/button/a/span")).click();

      WebElement name = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/div/div[2]/div/div/div[2]/h1"));
        String nameText = name.getText();

      assertEquals ("Егор Зарубов", nameText, "Имя пользователя не совпадает с ожидаемым");
      driver.quit();
    }
  }
}

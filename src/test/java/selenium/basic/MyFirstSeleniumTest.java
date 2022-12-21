package selenium.basic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class MyFirstSeleniumTest {
    private WebDriver driver;

    @BeforeClass
    public void setUpBrowser(ITestContext context) {
        // ustalenie ściezki do chromedriver
        System.setProperty("webdriver.chrome.driver","chromedriver.exe"); // ustawiecie siężcki do chromedrivera, musi być zdefiniowane w metodzie, podana ścieżka - pierwsza częśc na sztywno, druga faktyczna lokalizacja
        //Umożliwienie wstrzykiwnia własnej zawartości do raportu html
        System.setProperty("org.uncommons.reportng.escape-output", "false");
        // uruchomienie przeglądarki
        driver = new ChromeDriver(); // intrukcja odpala nam przeglądarkę
        //Zapis drivera do kontekstu
        context.getSuite().setAttribute("driver",driver);
        //Ustawienie domyślnego timeoutu dla Selenium - program będzie cekał tyle czasu
        //na KAŻDY element na stronie
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        //maksymalizacja okna
        driver.manage().window().maximize();
        //wejdź na podany adres
        driver.get("https://automationexercise.com/");
    }
    @Test
    public void automationExerciseLaunchTest() //throws InterruptedException {
//        Thread.sleep(3000);
    {
        List<WebElement> headerList = driver.findElements(By.id("header1"));
        Assert.assertNotEquals(headerList.size(),0, "Header section was not displayed");
        //driver.findElement(By.id("header1")); //znajdz ten element na stronie
    }
    @AfterClass
    public void tearDown() {
        //zamknięcie okna przeglądarki i połączenia między naszym kodem a chromedriver
        driver.quit();
    }
}

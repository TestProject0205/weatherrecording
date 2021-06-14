package mypackage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumWeather {
    WebDriver driver = new ChromeDriver();
    String baseUrl = "https://weather.com/";

    private static String tempKelvin;

    public void waitfor(By locator, WebDriver driver, int timeout) {
        final WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.refreshed(
                ExpectedConditions.elementToBeClickable(locator)));
    }

    public void setup() {

        // Launch https://weather.com
        driver.get(baseUrl);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public void tearDown() {
        driver.close();
    }

    public float getSiteTemperature(String city) {
        // Setting up web browser and loading https://weather.com
        setup();
        float temp = getSeleniumTemperature(city);
        tearDown();
        return temp;
    }

    public float getSeleniumTemperature(String city) {
        // Css Selector for Search Bar
        By cssElementSearch = By.cssSelector("input#LocationSearch_input");
        // Waiting for Search Bar to render
        waitfor(cssElementSearch, driver, 30);
        WebElement search = driver.findElement(cssElementSearch);
        // Clicking on Search Bar
        search.click();
        // Typing city into search bar
        search.sendKeys(city);
        try {
            driver.findElement(By.xpath(String.format("//button[contains(@id,'listbox-0')]"))).click();
        } catch (NoSuchElementException e) {
            System.out.println("Element not found.." + e.toString());
            return -1;
        }
        // CSS Selector for Temperature
        By cssElementTypeValue = By.cssSelector("span.CurrentConditions--tempValue--MHmYY");
        waitfor(cssElementTypeValue, driver, 30);
        WebElement typeValue = driver.findElement(cssElementTypeValue);
        String tempValue = typeValue.getAttribute("innerHTML");

        // Converting Temperature from Celsius to Kelvin
        Utils ut = new Utils();
        float tempKelvin = -1;
        try {
            tempKelvin = ut.convertCLToKel(tempValue);
            return tempKelvin;
        } catch (Exception e) {
            System.out.println("Error in temperature conversion...Exception:" + e.toString());
        }
        return -1;
    }
}
package com.example.softwaretestingproject;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.http.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import static org.junit.jupiter.api.Assertions.*;


public class CalculatorTest {
    private static final String url = "https://www.calculatorsoup.com/calculators/math/basic.php";
    private WebDriver driver;


    @BeforeEach
    public void setup() {
        //WebDriverManager.chromedriver().setup();
        //driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(url);
    }
    @Test
    public void testCalculatorAddition() {
        // Find the input fields and buttons
        WebElement cs_seven = driver.findElement(By.name("cs_seven"));
        WebElement cs_add = driver.findElement(By.name("cs_add"));
        WebElement cs_display = driver.findElement(By.name("cs_display"));

        // Enter the first operand (7)
        cs_seven.sendKeys("7");

        // Select the addition operator
        cs_add.click();

        // Enter the second operand (7)
        cs_seven.sendKeys("7");

        // Calculate the result
        cs_seven.sendKeys(Keys.RETURN);

        // Get the result from the display
        String result = cs_display.getAttribute("value");

        // Assert that the result is 14
        String expectedResult = "14";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testCalculatorMultiplication() {
        // Find the input fields and buttons
        WebElement cs_seven = driver.findElement(By.name("cs_seven"));
        WebElement cs_multiply = driver.findElement(By.name("cs_multiply"));
        WebElement cs_display = driver.findElement(By.name("cs_display"));

        // Enter the first operand (7)
        cs_seven.sendKeys("7");

        // Select the multiplication operator
        cs_multiply.click();

        // Enter the second operand (7)
        cs_seven.sendKeys("7");

        // Calculate the result
        cs_seven.sendKeys(Keys.RETURN);

        // Get the result from the display
        String result = cs_display.getAttribute("value");

        // Assert that the result is 49
        String expectedResult = "49";
        assertEquals(expectedResult, result);
    }
    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

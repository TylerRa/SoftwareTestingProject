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
    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    //input 1
    public void testAddition() {
        performCalculation("cs_seven", "cs_add", "cs_seven", "cs_equal");
        assertResult("14");
    }

    @Test
    //input 2
    public void testSubtraction() {
        performCalculation("cs_six", "cs_subtract", "cs_six", "cs_equal");
        assertResult("0");
    }

    @Test
    //input 3
    public void testMultiplication() {
        performCalculation("cs_seven", "cs_multiply", "cs_seven", "cs_equal");
        assertResult("49");
    }

    @Test
    //input 4
    public void testDivision() {
        performCalculation("cs_eight", "cs_divide", "cs_zero", "cs_equal");
        assertResult("NaN");
    }

    @Test
    //input 5
    public void testPower() {
        performCalculation("cs_five", "cs_power", "cs_two", "cs_equal");
        assertResult("25");
    }

    @Test
    //input 6
    public void testPercentage() {
        performCalculation("cs_five", "cs_percent", "cs_equal");
        assertResult("0.05");
    }
    @Test
    //input 7
    public void testPi() {
        performCalculation("cs_pi", "cs_equal");
        assertResult("3.1415926536");
    }

    @Test
    //input 8
    public void testSquareRoot() {
        performCalculation("cs_four", "cs_root2", "cs_equal");
        assertResult("2");
    }

    @Test
    //input 9
    public void testRound0() {
        performCalculation("cs_two","cs_decimal","cs_one","cs_two","cs_three","cs_four", "cs_five", "cs_round0");
        assertResult("2");
    }

    @Test
    //input 10
    public void testRound2() {
        performCalculation( "cs_two","cs_decimal","cs_one","cs_two","cs_three","cs_four", "cs_five", "cs_round2");
        assertResult("2.12");
    }

    @Test
    //input 11
    public void testAllClearButton() {
        performCalculation("cs_seven", "cs_decimal", "cs_one",  "cs_add", "cs_seven", "cs_decimal", "cs_one");

        WebElement button = driver.findElement(By.id("clear_display"));
        button.click();
        button.click();
        performCalculation("cs_equal");
        assertResult("0");
    }
    @Test
    //input 12
    public void testClearButton() {
        performCalculation("cs_negate","cs_seven", "cs_decimal", "cs_one",  "cs_add", "cs_negate", "cs_seven", "cs_decimal", "cs_one");
        WebElement button = driver.findElement(By.id("clear_display"));
        button.click();
        performCalculation("cs_equal");
        assertResult("-7.1");
    }

    @Test
    //input 13
    public void testMemoryClearEmpty() {
        performMemoryOperation("cs_m_clear", "cs_m_recall");
        assertResult("0");
    }

    @Test
    //input 14
    public void testMemoryClearNotEmpty() {
        performMemoryOperation("cs_seven", "cs_m_plus", "cs_m_clear", "cs_m_recall");
        assertResult("0");
    }

    @Test
    //input 15
    public void testAddMemoryNegativeInteger() {
        performMemoryOperation("cs_seven","cs_decimal", "cs_seven", "cs_m_plus", "cs_m_recall");
        assertResult("7.7");
    }

    @Test
    //input 16
    public void testAddMemoryNegativeDecimal() {
        performMemoryOperation("cs_negate", "cs_seven","cs_decimal","cs_five", "cs_m_plus", "cs_m_recall");
        assertResult("-7.5");
    }

    @Test
    //input 17
    public void testAddMemoryPositiveDecimal() {
        performMemoryOperation("cs_seven","cs_decimal","cs_four" ,"cs_m_plus", "cs_m_recall");
        assertResult("7.4");
    }

    @Test
    //input 18
    public void testMinusMemoryPositiveInteger() {
        performMemoryOperation("cs_six", "cs_m_minus", "cs_m_recall");
        assertResult("-6");
    }

    @Test
    //input 19
    public void testMinusMemoryNegativeInteger() {
        performMemoryOperation("cs_negate", "cs_six", "cs_m_minus", "cs_m_recall");
        assertResult("6");
    }

    @Test
    //input 20
    public void testMinusMemoryNegativeDecimal() {
        performMemoryOperation("cs_negate", "cs_two","cs_decimal", "cs_two", "cs_m_minus", "cs_m_recall");
        assertResult("2.2");
    }

    @Test
    //input 21
    public void testMinusMemoryPositiveDecimal() {
        performMemoryOperation("cs_two","cs_decimal","cs_three", "cs_m_minus", "cs_m_recall");
        assertResult("-2.3");
    }


    private void performCalculation(String... inputs) {
        for (String input : inputs) {
            WebElement button = driver.findElement(By.name(input));
            button.click();
        }
    }

    private void assertResult(String expected) {
        WebElement resultField = driver.findElement(By.id("cs_display")); // Replace with actual name
        String actual = resultField.getAttribute("value");

        assertEquals(expected, actual);
    }

    private void performMemoryOperation(String... inputs) {
        performCalculation(inputs);
    }
}
package com.framework;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyFirstUITestRefactoredAll {

    public static final String BASE_URL = "https://github.com/";
    static WebDriver driver;

    @BeforeAll  //@BeforeMethod in TestNG
    static void setup()
    {
        System.setProperty("webdriver.chrome.driver", "c:\\chromedriver.exe");
        ChromeOptions options =new ChromeOptions().addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @AfterAll  //@AfterMethod in TestNG
    static void cleanup()

    {
        driver.close();
    }

    @Test
    void userNameIsCorrectOnOverviewTab()
    {
        //Arrange

        String user ="andrejs-ps";
        driver.get(BASE_URL + user);

        //Act

        String actualUserName = driver.findElement(By.className("p-nickname")).getText();

        //Assert

        assertEquals(user, actualUserName);
        System.out.println(user + " is matching with " + actualUserName);

    }

    @Test
    void repoLinkGoesToCorrectRepo()
    {
        //Arrange
        String user ="andrejs-ps";
        driver.get(BASE_URL + user);

        //Act
        String repo = "automated-tests-in-java-with-fluent-interface-using-webdriver-selenium";
        WebElement repoLink = driver.findElement(By.linkText(repo));

        repoLink.click();

        String actualURL = driver.getCurrentUrl();

        //Assert
        assertEquals( "https://github.com/andrejs-ps/" + repo, actualURL);

    }

    @Test
    void repositoryCountIsCorrect()
    {
        //Arrange

        //Act
        driver.get("https://github.com/andrejs-ps?tab=repositories");
        List<WebElement> repos = driver.findElements(By.xpath("//div[@id='user-repositories-list']//li"));

        //Assert
        assertEquals(9, repos.size());

    }

}

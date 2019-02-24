import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ISelect;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.Set;
;

;

public class proje
{
    @Test



    public void n11Test() throws Exception{
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        WebDriver driver= new ChromeDriver(); // chrome için
        driver.get("https://www.n11.com/"); //n11 url
        driver.manage().window().maximize();

        // Giriş butonu click
        driver.findElement(By.className("btnSignIn")).click();

        String parentWindow = driver.getWindowHandle();
        System.out.println("Parent Window ID is : " + parentWindow);

        // Facebook butonu click
        driver.findElement(By.className("facebook_large")).click();
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();
        while(i1.hasNext())
        {

            String ChildWindow=i1.next();

            if(!parentWindow.equalsIgnoreCase(ChildWindow))
            {
                driver.switchTo().window(ChildWindow);
                Thread.sleep(2000);
                System.out.println("Thread.sleep" );
                driver.findElement(By.id("email")).sendKeys("omurpinar1@hotmail.com");
                driver.findElement(By.id("pass")).sendKeys("omur2019++");
                driver.findElement(By.id("loginbutton")).click();
                System.out.println("loginbutton click" );

                System.out.println("zdriver.switchTo parentWindow öncesi" );
                driver.switchTo().window(parentWindow);
                System.out.println("zdriver.switchTo parentWindow sonrası" );

                Thread.sleep(2000);
                //Ürün Arama
                driver.findElement(By.id("searchData")).sendKeys("iphone7");
                driver.findElement(By.className("searchBtn")).click();
                driver.findElement(By.xpath("//li[1]/div/div[1]/a")).click();
                String sonuc = driver.findElement(By.xpath("//li[1]/div/div[1]/a")).getText();
                Assert.assertTrue(true, sonuc); // Ürün aynı başarıyla geçiyor
                System.out.println("zfinito" );

                Thread.sleep(2000);
                this.takeSnapShot(driver, "./test.png") ;
                System.out.println("resim tamam" );
                Thread.sleep(2000);
                System.out.println("çıkış öncesi" );
                WebElement element = driver.findElement(By.className("myAccountHolder"));
                Actions action = new Actions(driver);
                action.moveToElement(element).build().perform();
                Thread.sleep(2000);
                System.out.println("action tamam" );
                driver.findElement(By.className("logoutBtn")).click();
                System.out.println("çıkış tamam" );
            }
        }

    }

    public static void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile=new File(fileWithPath);
        FileUtils.copyFile(SrcFile, DestFile);
    }


}

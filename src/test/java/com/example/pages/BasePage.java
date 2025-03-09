package com.example.pages;

import com.example.utils.LogUtil;
import com.example.utils.PopupHandler;
import jakarta.annotation.PostConstruct;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    @Autowired
    protected WebDriver driver;

    @Autowired
    protected WebDriverWait wait;

    @Autowired
    private PopupHandler popupHandler;

    @Value("${default.timeout}")
    private int timeout;

    @Autowired
    protected JavascriptExecutor javascriptExecutor;

    @Autowired
    protected LogUtil logUtil;

    @PostConstruct
    private void init() {
        PageFactory.initElements(this.driver, this);
    }

    public abstract boolean isAt();

    public <T> void waitElement(T elementAttr) {
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            wait.until(ExpectedConditions.presenceOfElementLocated((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
        }
    }

    public <T> void waitElements(T elementAttr) {
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
        } else {
            wait.until(ExpectedConditions.visibilityOfAllElements((WebElement) elementAttr));
        }
    }

    //Click Method by using JAVA Generics (You can use both By or Web element)
    public <T> void click(T elementAttr) {
        waitElement(elementAttr);
        popupHandler.closePopupIfExists();
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            driver
                .findElement((By) elementAttr)
                .click();
        } else {
            ((WebElement) elementAttr).click();
        }
    }

    public void jsClick(By by) {
        javascriptExecutor.executeScript("arguments[0].click();", wait.until(ExpectedConditions.visibilityOfElementLocated(by)));
    }

    //Write Text by using JAVA Generics (You can use both By or WebElement)
    public <T> void writeText(T elementAttr, String text) {
        waitElement(elementAttr);
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy((By) elementAttr));
            driver
                .findElement((By) elementAttr)
                .sendKeys(text);
        } else {
            wait.until(ExpectedConditions.visibilityOf((WebElement) elementAttr));
            ((WebElement) elementAttr).sendKeys(text);
        }
    }

    //Read Text by using JAVA Generics (You can use both By or WebElement)
    public <T> String readText(T elementAttr) {
        if (elementAttr
            .getClass()
            .getName()
            .contains("By")) {
            return driver
                .findElement((By) elementAttr)
                .getText();
        } else {
            return ((WebElement) elementAttr).getText();
        }
    }

    public static void waitForPageToLoad(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (true) {
            String readyState = (String) js.executeScript("return document.readyState;");
            if (readyState.equals("complete")) {
                break;
            }
        }
    }

    public static void waitForAjax(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        while (true) {
            Long ajaxCalls = (Long) js.executeScript("return jQuery.active;");
            if (ajaxCalls == 0) {
                break;
            }
        }
    }

    public void waitForElementToBeClickable(WebDriver driver, By locator) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

//    @SneakyThrows
//    public <T> String readTextErrorMessage(T elementAttr) {
//        Thread.sleep(5000); //This needs to be improved.
//        return driver
//            .findElement((By) elementAttr)
//            .getText();
//    }

//    //Close popup if exists
//    public void handlePopup(By by) throws InterruptedException {
//        waitElements(by);
//        List<WebElement> popup = driver.findElements(by);
//        if (!popup.isEmpty()) {
//            popup
//                .get(0)
//                .click();
//            Thread.sleep(200);
//        }
//    }
}

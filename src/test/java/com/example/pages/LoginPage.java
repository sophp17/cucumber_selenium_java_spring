package com.example.pages;

import com.example.annotations.LazyComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static org.junit.jupiter.api.Assertions.assertEquals;

@LazyComponent
public class LoginPage extends BasePage {
    @FindBy(how = How.CSS, using = "input[name=\"username\"]")
    public WebElement userName;

    @FindBy(how = How.CSS, using = "input[name=\"password\"]")
    public WebElement password;

    By loginButtonBy          = By.cssSelector("button[type=\"submit\"]");
    By errorMessageIdAndPasswordBy = By.className("v-alert__content");
    By errorMessagePasswordBy = By.xpath("//*[@id=\"loginForm\"]/div[2]/div/div");
    By errorMessagePasswordCssBy = By.cssSelector("div[data-errormessagefor='password'] > .errorText");

    public void login(String userName, String password) throws InterruptedException {
        waitForPageToLoad(driver);
        //Thread.sleep(3000);
        this.userName.sendKeys(userName);
        this.password.sendKeys(password);
        driver.findElement(loginButtonBy).click();
    }


    public LoginPage verifyPasswordErrorMessage(String expectedText) {
        assertEquals(expectedText, readText(errorMessagePasswordBy));
        return this;
    }

    public LoginPage verifyPasswordErrorMessageWithCss(String expectedText) {
        //assertEquals(expectedText, readTextErrorMessage(errorMessagePasswordCssBy));
        return this;
    }

    public LoginPage verifyLogEntryFailMessage() {
        logUtil.isLoginErrorLog(driver);
        return this;
    }

    public LoginPage verifyIdAndPasswordrrorMessage(String expectedText) {
        assertEquals(expectedText, readText(errorMessageIdAndPasswordBy));
        return this;
    }

    @Override public boolean isAt() {
        return this.wait.until((d) -> this.userName.isDisplayed());
    }
}

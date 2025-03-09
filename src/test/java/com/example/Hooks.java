package com.example;

import com.example.pages.BasePage;
import com.example.utils.CookieService;
import com.example.utils.PopupHandler;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class Hooks extends BasePage {

    @Value("${application.url}")
    private String baseURL;

    @Autowired
    private PopupHandler popupHandler;

    @Autowired
    CookieService cookieService;

    @Before
    public void goToHomePage(){
        driver.get(baseURL);
        cookieService.addCookies(driver);
    }

   @After
    public void closeDriver(){
    driver.quit();
   }

    @Override
    public boolean isAt() {
        return false;
    }
}

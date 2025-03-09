package com.example.pages;

import com.example.annotations.LazyComponent;
import com.example.utils.CookieService;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

@LazyComponent
public class HomePage extends BasePage {
    @Autowired
    LoginPage loginPage;

    @Autowired
    CookieService cookieService;

    @Value("${application.url}")
    private String baseURL;


    @FindBy(how = How.CSS, using = "a.v-btn.ok-btn[aria-label=\"Se connecter\"]")
    public WebElement signInButton;

    By homePageLogo = By.cssSelector("a[alt=\"Accueil\"] img[alt=\"Ouedkniss\"]");
    By slideElements = By.cssSelector(".o-mega-menu .swiper-slide a");
    By sideMenuBtnBy = By.cssSelector("button[aria-label=\"Menu\"]");
    By sideMenuElementBy = By.cssSelector(".ok-list-item");
    By elementSideMenu = By.xpath("//span[@class='__title' and text()='Services']");

    public void openSlideMenu() throws InterruptedException {
        waitElement(elementSideMenu);
        scrollToElement(driver, driver.findElement(elementSideMenu));
        //Thread.sleep(2000);
        click(elementSideMenu);
        //Thread.sleep(2000);
    }

    public void goToHomePage(){
        if (!driver.getCurrentUrl().equals(baseURL)){
            driver.get(baseURL);
            System.out.println("redirection vers la homepage");
        } else {
            System.out.println("déjà sur la homepage");
        }
    }

    public boolean isRedirection(String category){
        return driver.getCurrentUrl().contains(category);
    }

    public void clickOnElementSideMenu() {
        waitElement(sideMenuBtnBy);
        click(sideMenuBtnBy);
        waitElements(sideMenuElementBy);
    }

    public List<String> getElementsPresent(List<String> expectedElements, By locator) throws InterruptedException {
        waitElements(locator);
        //Thread.sleep(3000);
        List<WebElement> elements = driver.findElements(locator);
        return elements.stream()
                .map(WebElement::getText)
                .filter(expectedElements::contains)
                .collect(Collectors.toList());
    }

    public List<String> getElementsPresentOnSlide(List<String> expectedElements) throws InterruptedException {
        return getElementsPresent(expectedElements, slideElements);
    }

    public List<String> getElementPresentOnSideMenu(List<String> expectedElements) throws InterruptedException {
        driver.get(baseURL);
        //Thread.sleep(3000);
        clickOnElementSideMenu();
        return getElementsPresent(expectedElements, sideMenuElementBy);
    }


    public List<String> getMissingElements(List<String> expectedElements, List<String> elementsPresent) {
        return expectedElements.stream()
                .filter(el-> !elementsPresent.contains(el))
                .collect(Collectors.toList());
    }

    public List<String> getMissingElementsOnSlide(List<String> expectedElements) throws InterruptedException {
        List<String> elementsPresentOnSideMenu = getElementPresentOnSideMenu(expectedElements);
        return getMissingElements(expectedElements,elementsPresentOnSideMenu);
    }

    public List<String> getMissingElementsOnSideMenu(List<String> expectedElements) throws InterruptedException {
        List<String> elementsPresentOnSlide = getElementsPresentOnSlide(expectedElements);
        return getMissingElements(expectedElements, elementsPresentOnSlide);
    }

    @Override
    public boolean isAt() {
        return this.wait.until((d) -> this.signInButton.isDisplayed());
    }

}
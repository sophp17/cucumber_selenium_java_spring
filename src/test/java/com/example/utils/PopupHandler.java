package com.example.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Component
public class PopupHandler {

    private static final int POPUP_TIMEOUT_SECONDS = 3; // Timeout pour la détection des popups
    private final WebDriver driver;

    @Autowired
    public PopupHandler(WebDriver driver) {
        this.driver = driver;
    }

    public void closePopupIfExists() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            List<WebElement> popups = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getPopupSelectors()));

            for (WebElement popup : popups) {
                if (isPopup(popup)) {
                    WebElement closeButton = getClickableCloseButton();
                    if (closeButton != null) {
                        closeButton.click();
                        System.out.println("Popup fermée avec succès.");
                        return;
                    }
                }
            }
        } catch (TimeoutException e) {
            System.out.println("Aucune popup détectée.");
        }
    }

    private boolean isPopup(WebElement element) {
        String tagName = element.getTagName().toLowerCase();
        String classAttr = element.getAttribute("class");
        String roleAttr = element.getAttribute("role");
        String ariaHidden = element.getAttribute("aria-hidden");

        return (tagName.equals("div") || tagName.equals("section")) &&
                (classAttr != null && (classAttr.contains("popup") || classAttr.contains("modal") || classAttr.contains("overlay") || classAttr.contains("dialog"))) &&
                (roleAttr == null || !roleAttr.equalsIgnoreCase("dialog")) &&
                (ariaHidden == null || !ariaHidden.equalsIgnoreCase("true"));
    }

    private WebElement getClickableCloseButton() {
        List<WebElement> buttons = driver.findElements(getCloseButtonSelectors());

        for (WebElement button : buttons) {
            if (button.isDisplayed() && button.isEnabled()) {
                return button;
            }
        }
        return null;
    }

    private By getPopupSelectors() {
        return By.cssSelector("div[class*='popup'], div[class*='modal'], div[class*='overlay'], div[role='dialog']");
    }

    private By getCloseButtonSelectors() {
        return By.cssSelector("button[aria-label*='fermer'], button[aria-label*='close'], button[aria-label*='Autoriser'], button[class*='close'], button[class*='dismiss'], button[class*='exit']");
    }
}

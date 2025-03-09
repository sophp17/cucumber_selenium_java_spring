package com.example.config;

import com.example.annotations.LazyConfiguration;
import com.example.annotations.WebdriverScopeBean;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

@Profile("grid")  // Active cette configuration uniquement si le profil "grid" est activé
@LazyConfiguration
public class RemoteWebDriverConfig {

    private static final Logger LOGGER = Logger.getLogger(RemoteWebDriverConfig.class.getName());
    @Value("${selenium.grid.url}")
    private String seleniumGridUrl;
    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox", matchIfMissing = true)
    @Primary
    public WebDriver remoteFirefoxDriver() {
        Logger LOGGER = Logger.getLogger(RemoteWebDriverConfig.class.getName());
        try {
            LOGGER.info("Initializing Remote Firefox WebDriver...");

            FirefoxOptions firefoxOptions = new FirefoxOptions();

            Proxy proxy = new Proxy();
            proxy.setAutodetect(false);
            proxy.setNoProxy(System.getenv("NO_PROXY")); // ✅ Récupération dynamique

            firefoxOptions.setCapability("proxy", proxy);
            firefoxOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            firefoxOptions.addArguments("--start-maximized"); // Ouvrir en plein écran

            LOGGER.info("Connecting to Selenium Grid at: " + seleniumGridUrl);
            WebDriver driver = new RemoteWebDriver(new URL(seleniumGridUrl), firefoxOptions);

            LOGGER.info("Successfully initialized Remote Firefox WebDriver.");
            return driver;

        } catch (MalformedURLException e) {
            LOGGER.severe("Malformed Selenium Grid URL: " + seleniumGridUrl);
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }


    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome", matchIfMissing = false)
    @Primary
    public WebDriver remoteChromeDriver() {
        Logger LOGGER = Logger.getLogger(RemoteWebDriverConfig.class.getName());
        try {
            LOGGER.info("Initializing Remote Firefox WebDriver...");

            ChromeOptions chromeOptions = new ChromeOptions();

            Proxy proxy = new Proxy();
            proxy.setAutodetect(false);
            proxy.setNoProxy(System.getenv("NO_PROXY")); // ✅ Récupération dynamique
            chromeOptions.setCapability("proxy", proxy);
            chromeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            chromeOptions.addArguments("--start-maximized"); // Ouvrir en plein écran
            LOGGER.info("Connecting to Selenium Grid at: " + seleniumGridUrl);
            WebDriver driver = new RemoteWebDriver(new URL(seleniumGridUrl), chromeOptions);

            LOGGER.info("Successfully initialized Remote Firefox WebDriver.");
            return driver;

        } catch (MalformedURLException e) {
            LOGGER.severe("Malformed Selenium Grid URL: " + seleniumGridUrl);
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }


    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge", matchIfMissing = false)
    @Primary
    public WebDriver remoteEdgeDriver() {
        Logger LOGGER = Logger.getLogger(RemoteWebDriverConfig.class.getName());
        try {
            LOGGER.info("Initializing Remote Firefox WebDriver...");

            EdgeOptions edgeOptions = new EdgeOptions();

            Proxy proxy = new Proxy();
            proxy.setAutodetect(false);
            proxy.setNoProxy(System.getenv("NO_PROXY")); // ✅ Récupération dynamique
            edgeOptions.setCapability("proxy", proxy);
            edgeOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            edgeOptions.addArguments("--start-maximized"); // Ouvrir en plein écran
            edgeOptions.addArguments("--width=1920", "--height=1080"); // Définit une taille de fenêtre
            LOGGER.info("Connecting to Selenium Grid at: " + seleniumGridUrl);
            WebDriver driver = new RemoteWebDriver(new URL(seleniumGridUrl), edgeOptions);

            LOGGER.info("Successfully initialized Remote Firefox WebDriver.");
            return driver;

        } catch (MalformedURLException e) {
            LOGGER.severe("Malformed Selenium Grid URL: " + seleniumGridUrl);
            throw new RuntimeException("Invalid Selenium Grid URL", e);
        }
    }
}

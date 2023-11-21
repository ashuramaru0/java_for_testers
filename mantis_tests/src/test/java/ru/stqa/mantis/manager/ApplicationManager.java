package ru.stqa.mantis.manager;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.GeckoDriverService;

import java.util.Properties;

public class ApplicationManager {
    private WebDriver driver;
    private String string;
    private Properties properties;
    private SessionHelper sessionHelper;
    private HttpSessionHelper httpSessionHelper;
    private JamesCliHelper jamesCliHelper;
    private MailHelper mailHelper;
    private JamesApiHelper jamesApiHelper;
    private DeveloperMailHelper developerMailHelper;
    private RestApiHelper restApiHelper;
    private SoapApiHelper soapApiHelper;

    public void init(String browser, Properties properties) {
        this.string = browser;
        this.properties = properties;
    }

    public WebDriver driver() {
        if (driver == null) {
            if ("chrome".equals(string)) {
                var service = ChromeDriverService.createDefaultService();
                service.setExecutable("c:/windows/system32/chromedriver.exe");
                driver = new ChromeDriver(service);
            } else if ("firefox".equals(string)) {
                var service = GeckoDriverService.createDefaultService();
                service.setExecutable("c:/windows/system32/geckodriver.exe");
                driver = new FirefoxDriver(service);
            } else {
                throw new IllegalArgumentException(String.format("Unknown browser %f", string));
            }
            Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
            driver.get(properties.getProperty("web.baseUrl"));
            driver.manage().window().setSize(new Dimension(1000, 1000));
        }
        return driver;
    }

    public SessionHelper session() {
        if (sessionHelper == null) {
            sessionHelper = new SessionHelper(this);
        }
        return sessionHelper;
    }

    public HttpSessionHelper http() {
        if (httpSessionHelper == null) {
            httpSessionHelper = new HttpSessionHelper(this);
        }
        return httpSessionHelper;
    }

    public JamesCliHelper jamesCli() {
        if (jamesCliHelper == null) {
            jamesCliHelper = new JamesCliHelper(this);
        }
        return jamesCliHelper;
    }

    public JamesApiHelper jamesApi() {
        if (jamesApiHelper == null) {
            jamesApiHelper = new JamesApiHelper(this);
        }
        return jamesApiHelper;
    }

    public DeveloperMailHelper developerMail() {
        if (developerMailHelper == null) {
            developerMailHelper = new DeveloperMailHelper(this);
        }
        return developerMailHelper;
    }

    public MailHelper mail() {
        if (mailHelper == null) {
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }

    public RestApiHelper rest() {
        if (restApiHelper == null) {
            restApiHelper = new RestApiHelper(this);
        }
        return restApiHelper;
    }

    public SoapApiHelper soap() {
        if (soapApiHelper == null) {
            soapApiHelper = new SoapApiHelper(this);
        }
        return soapApiHelper;
    }

    public String property(String name) {
        return properties.getProperty(name);
    }
}

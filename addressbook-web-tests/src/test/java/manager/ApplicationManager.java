package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.Properties;

public class ApplicationManager {
    protected static WebDriver driver;
    private LoginHelper session;

    private GroupHelper groups;

    private ContactHelper contact;

    private JdbcHelper jdbc;

    private Properties properties;

    public void init(String browser, Properties properties) {
        this.properties = properties;
        if (driver == null){
            if("chrome".equals(browser)){
                driver = new ChromeDriver();
            } else if("firefox".equals(browser)){
                    driver = new FirefoxDriver();
            } else {
                throw new IllegalArgumentException(String.format("Unkwon browser %s", browser));
            }
          Runtime.getRuntime().addShutdownHook(new Thread(driver::quit));
          driver.get(properties.getProperty("web.baseUrl"));
          driver.manage().window().setSize(new Dimension(1936, 1048));
            session().login(properties.getProperty("web.username"), properties.getProperty("web.password"));
        }
    }

    public LoginHelper session(){
        if (session == null){
            session = new LoginHelper(this);
        }
        return session;
    }

    public JdbcHelper jdbc(){
        if (jdbc == null){
            jdbc = new JdbcHelper(this);
        }
        return jdbc;
    }

    public GroupHelper groups(){
        if (groups == null){
            groups = new GroupHelper(this);
        }
        return groups;
    }

    public ContactHelper contact(){
        if (contact == null){
            contact = new ContactHelper(this);
        }
        return contact;
    }

    protected boolean isElementPresent(By locator) {
      try {
        driver.findElement(locator);
        return true;
      } catch (NoSuchElementException exception) {
        return false;
      }
    }

}

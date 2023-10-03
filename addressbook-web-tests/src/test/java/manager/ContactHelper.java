package manager;

import model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillConcatForm(contact);
        submitContactCreation();
        returnToContactPage();
    }

    public void removeContact(){
        openContactPage();
        selectContact();
        removeSelectedContact();
        acceptAlert();
    }

    private void acceptAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void removeSelectedContact() {
        click(By.xpath("//div[2]/input"));
    }

    private void selectContact() {
        click(By.name("selected[]"));
    }

    private void fillConcatForm(ContactData contact) {

        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.phone());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void returnToContactPage() {
        click(By.linkText("home page"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void openContactPage() {
        if(! manager.isElementPresent(By.cssSelector("input:nth-child(7)"))){
            click(By.linkText("home"));
        }
    }
    public boolean isContactPresent() {
        openContactPage();
        return manager.isElementPresent(By.name("selected[]"));
    }

}

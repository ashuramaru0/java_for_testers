package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void createContact(ContactData contact) {
        openContactPage();
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    public void removeContact(ContactData contact){
        openContactPage();
        selectContact(contact);
        removeSelectedContact();
        acceptAlert();
        returnToContactsPage();
    }

    public void modifyContact(ContactData contact, ContactData modifiedContact) {
        openContactPage();
        selectContact(contact);
        InitContactModification(contact);
        fillContactForm(modifiedContact);
        submitContactModification();
        returnToContactsPage();
    }

    private void submitContactModification() {
        click(By.name("update"));
    }

    private void InitContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));;
    }


    private void returnToContactsPage() {
        click(By.linkText("home"));
    }
    private void acceptAlert() {
        manager.driver.switchTo().alert().accept();
    }

    private void removeSelectedContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    private void fillContactForm(ContactData contact) {

        type(By.name("firstname"), contact.firstName());
        type(By.name("lastname"), contact.lastName());
        type(By.name("address"), contact.address());
        type(By.name("email"), contact.email());
        type(By.name("mobile"), contact.phone());
        attach(By.name("photo"), contact.photo());
    }

    private void submitContactCreation() {
        click(By.name("submit"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void openContactPage() {
        if(! manager.isElementPresent(By.cssSelector("input:nth-child(7)"))){
            click(By.linkText("home"));
        }
    }
    public int getCount() {
        openContactPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void removeAllContacts() {
        selectAllContacts();
        removeSelectedContact();
    }
    private void selectAllContacts() {
        var checkboxes =  manager.driver.findElements(By.name("selected[]"));
        for (var checkbox: checkboxes) {
            checkbox.click();
        }
    }

    public List<ContactData> getContactList() {
        openContactPage();
        var contact = new ArrayList<ContactData>();
        var tableRows = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var tableRow : tableRows) {
            var lastname = tableRow.findElement(By.cssSelector("td:nth-child(2)")).getText();
            var firstname = tableRow.findElement(By.cssSelector("td:nth-child(3)")).getText();
            var checkbox = tableRow.findElement(By.name("selected[]"));
            var id = checkbox.getAttribute("value");
            contact.add(new ContactData().withId(id).withFirstName(firstname).withLastName(lastname));
        }
        return contact;
    }
}

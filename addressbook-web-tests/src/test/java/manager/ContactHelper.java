package manager;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase{
    public ContactHelper(ApplicationManager manager){
        super(manager);
    }

    public void createContact(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
        returnToContactsPage();
    }

    public void createContact(ContactData contact, GroupData group) {
        initContactCreation();
        fillContactForm(contact);
        selectGroup(group);
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


    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }
    private void submitContactModification() {
        click(By.name("update"));
    }

    private void InitContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", contact.id())));;
    }

    public void addToGroup(ContactData contact, GroupData group) {
        openContactPage();
        selectContact(contact);
        selectGroupAddTo(group);
        addContactToGroup();
        returnToHomePageAfterAddingOrRemovingGroup(group);
    }
    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openContactPage();
        showContactsInGroup(group);
        selectContact(contact);
        removeFromGroup();
        returnToHomePageAfterAddingOrRemovingGroup(group);
    }

    private void removeFromGroup() {
        click(By.name("remove"));
    }

    private void showContactsInGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    private void addContactToGroup() {
        click(By.name("add"));
    }
    private void returnToHomePageAfterAddingOrRemovingGroup(GroupData group) {
        click(By.linkText(String.format("group page \"%s\"", group.name())));
    }

    private void selectGroupAddTo(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
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

    public void refreshPage() {
        click(By.linkText("home"));
    }

}

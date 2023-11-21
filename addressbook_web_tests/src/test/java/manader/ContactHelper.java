package manader;

import model.ContactData;
import model.GroupData;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactHelper extends HelperBase {

    public ContactHelper(ApplicationManager manager) {
        super(manager);
    }

    public void openHomePage() {
        if (!manager.isElementPresent(By.name("searchstring"))) {
            click(By.linkText("home"));
        }
    }

    public void createContact(ContactData contact) {
        openHomePage();
        initContactCreation();
        fillContacttForm(contact);
        sudmitContactCreation();
        returnToContactPage();
    }

    public void createContact(ContactData contact, GroupData group) {
        openHomePage();
        initContactCreation();
        fillContacttForm(contact);
        selectGroup(group);
        sudmitContactCreation();
        returnToContactPage();
    }

    private void selectGroup(GroupData group) {
        new Select(manager.driver.findElement(By.name("new_group"))).selectByValue(group.id());
    }

    public void modifyContact(ContactData contact, ContactData modifiedFirstName) {
        openHomePage();
        initContactModification(contact);
        fillContacttForm(modifiedFirstName);
        updateContact();
        returnToContactPage();
    }

    private void returnToContactPage() {
        click(By.linkText("home page"));
    }

    private void initContactCreation() {
        click(By.linkText("add new"));
    }

    private void updateContact() {
        click(By.name("update"));
    }

    private void sudmitContactCreation() {
        click(By.name("submit"));
    }

    private void fillContacttForm(ContactData contact) {
        type(By.name("firstname"), contact.firstname());
        type(By.name("lastname"), contact.lastname());
        attach(By.name("photo"), contact.photo());
    }

    private void selectContact(ContactData contact) {
        click(By.cssSelector(String.format("input[value='%s']", contact.id())));
    }

    public void removeContact(ContactData contact) {
        openHomePage();
        selectAllGroupOnHomePage();
        selectContact(contact);
        deletContact();
        openHomePage();
    }

    public void deleteContactFromGroup(GroupData group) {
        click(By.linkText("home"));
        selectGroupForShowContacts(group);
        click(By.name("selected[]"));
        submitRemoveFromGroup();
        openHomePage();
    }

    public void addGroup(GroupData group) {
        click(By.linkText("home"));
        selectGroupForAddOnHomePage(group);
        click(By.name("selected[]"));
        submitAddFromGroup();
        click(By.linkText("home"));
    }

    private void selectAllGroupOnHomePage() {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue("");
    }

    private void selectGroupForAddOnHomePage(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
    }

    private void submitRemoveFromGroup() {
        click(By.xpath("//input[@name='remove']"));
    }

    private void initContactModification(ContactData contact) {
        click(By.cssSelector(String.format("a[href=\'edit.php?id=%s\']", contact.id())));
    }

    public void deletContact() {
        click(By.xpath("//input[@value=\'Delete\']"));
        manager.driver.switchTo().alert().accept();
    }

    public void removeAllContact() {
        click(By.linkText("home"));
        selectAllGroupOnHomePage();
        selectAllContacts();
        deletContact();
        openHomePage();
    }

    private void selectAllContacts() {
        manager.driver
                .findElements(By.name("selected[]"))
                .forEach(WebElement::click);
    }

    private void submitAddFromGroup() {
        click(By.xpath("//input[@name='add']"));
    }

    public List<ContactData> getList() {
        openHomePage();
        var contact = new ArrayList<ContactData>();
        var trs = manager.driver.findElements(By.xpath("//tr[@name=\'entry\']"));
        for (var tr : trs) {
            var id = tr.findElement(By.cssSelector("td:nth-child(1).center>input")).getAttribute("value");
            var lastName = tr.findElement(By.xpath("td[2]")).getText();
            var firstName = tr.findElement(By.xpath("td[3]")).getText();
            contact.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName));
        }
        return contact;
    }

    public List<ContactData> getList(GroupData group) {
        {
            openHomePage();
            new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
        }
        return getList();
    }

    public Map<String, String> getPhones() {
        openHomePage();
        var result = new HashMap<String, String>();
        List<WebElement> rows = manager.driver.findElements(By.name("entry"));
        for (WebElement row : rows) {
            var id = row.findElement(By.tagName("input")).getAttribute("id");
            var phones = row.findElements(By.tagName("td")).get(5).getText();
            result.put(id, phones);
        }
        return result;
    }

    public String getAddresses(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var address = manager.driver.findElement(By.name("address")).getText();
        var address2 = manager.driver.findElement(By.name("address2")).getText();

        return address + address2;
    }

    public String getAddress(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var address = manager.driver.findElement(By.name("address")).getText();
        return address;
    }

    public String getEmailsFromEditForm(ContactData contact) {
        openHomePage();
        selectContact(contact);
        initContactModification(contact);
        var email = manager.driver.findElement(By.name("email")).getAttribute("value");
        var email2 = manager.driver.findElement(By.name("email2")).getAttribute("value");
        var email3 = manager.driver.findElement(By.name("email3")).getAttribute("value");

        return email + email2 + email3;

    }

    public String getEmailsFromHomePage(ContactData contact) {
        openHomePage();
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[5]", contact.id()))).getText().replaceAll("\\r\\n|\\r|\\n", "");
    }

    public String getAddressFromHomePage(ContactData contact) {
        openHomePage();
        return manager.driver.findElement(By.xpath(
                String.format("//input[@id='%s']/../../td[4]", contact.id()))).getText().replaceAll("\\r\\n|\\r|\\n", "");
    }

    public void removeContactFromGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectGroupForShowContacts(group);
        selectContact(contact);
        manager.driver.findElement(By.name("remove")).click();
        manager.driver.findElement(By.cssSelector("div.msgbox"));
    }

    private void selectGroupForShowContacts(GroupData group) {
        new Select(manager.driver.findElement(By.name("group"))).selectByValue(group.id());
    }

    public void addContactToGroup(ContactData contact, GroupData group) {
        openHomePage();
        selectContact(contact);
        selectGroupForAddContact(group);
    }

    private void selectGroupForAddContact(GroupData group) {
        new Select(manager.driver.findElement(By.name("to_group"))).selectByValue(group.id());
        manager.driver.findElement(By.name("add")).click();
        manager.driver.findElement(By.cssSelector("div.msgbox"));
    }
}




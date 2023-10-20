package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTest extends TestBase{


    @Test
    public void canRemoveContact() {
        if (app.contact().getCount() == 0){
            app.contact().createContact(new ContactData("", "first name", "last name", "address", "email", "phone", " "));
        };
        var oldContact = app.contact().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        app.contact().removeContact(oldContact.get(index));
        var newContacts = app.contact().getContactList();
        var expectedList = new ArrayList<>(oldContact);
        expectedList.remove(index);
        Assertions.assertEquals(newContacts, expectedList);
    }
    @Test
    void canRemoveAllContactsAtOnce(){
        if (app.contact().getCount() == 0){
            app.contact().createContact(new ContactData("", "first name", "last name", "address", "email", "phone", " "));
        }
        app.contact().removeAllContacts();
        Assertions.assertEquals(0,app.contact().getCount());
    }
}

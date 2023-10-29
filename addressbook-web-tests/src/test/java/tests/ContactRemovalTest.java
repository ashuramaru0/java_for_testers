package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTest extends TestBase{


    @Test
    public void canRemoveContact() {
        if (app.hmb().getContactCount() == 0) {
            app.hmb().createContact(new ContactData()
                    .withFirstName("For removal")
                    .withLastName("For removal")
                    .withAddress("For removal")
                    .withEmail("For removal")
                    .withPhone("For removal")
            );
            app.contact().refreshPage();
            List<ContactData> oldContacts = app.hmb().getContactList();
            int index = new Random().nextInt(oldContacts.size());
            app.contact().removeContact(oldContacts.get(index));
            List<ContactData> newContacts = app.hmb().getContactList();
            List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
            expectedContacts.remove(index);
            Assertions.assertEquals(expectedContacts, newContacts);
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

package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ContactModificationTest extends TestBase{

    @Test
    public void canModifyContact() {
        if (app.hmb().getContactCount() == 0) {
            app.contact().createContact(CommonFunctions.randomContact());
        }

        List<ContactData> oldContacts = app.hmb().getContactList();
        int index = new Random().nextInt(oldContacts.size());
        ContactData modifiedContact = CommonFunctions.randomContact();

        app.contact().modifyContact(oldContacts.get(index), modifiedContact);
        List<ContactData> newContacts = app.hmb().getContactList();
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        expectedContacts.set(index, modifiedContact.withId(oldContacts.get(index).id()));
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        expectedContacts.sort(compareById);
        newContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
}

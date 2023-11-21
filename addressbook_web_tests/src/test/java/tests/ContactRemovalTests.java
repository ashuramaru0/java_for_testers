package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class ContactRemovalTests extends TestBase {

    @Test
    public void canRemoveContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        var oldContact = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        app.contact().removeContact(oldContact.get(index));
        var newContact = app.hbm().getContactList();
        var expectedTable = new ArrayList<>(oldContact);
        expectedTable.remove(index);
        Assertions.assertEquals(newContact, expectedTable);
    }

    @Test
    public void canRemoveAllContactsAtOnce() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }
        app.contact().removeAllContact();
        Assertions.assertEquals(0, app.hbm().getContactCount());
    }

    @Test
    public void canDeleteContactFromGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }


        var group = app.hbm().getGroupList().get(0);
        app.contact().addGroup(group);

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contact().deleteContactFromGroup(group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() - 1, newRelated.size());
    }

}


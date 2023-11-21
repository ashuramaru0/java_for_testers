package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class ContactModificationTests extends TestBase {

    @Test
    public void canModifyContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData("", "firstname", "lastname", "src/test/resources/images/avatar.png", "", "", "", "", "", "", "", "", ""));
        }
        var oldContact = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContact.size());
        var testData = new ContactData().withFirstName("modified firstname");
        app.contact().modifyContact(oldContact.get(index), testData);
        var newContact = app.hbm().getContactList();
        var expectedTable = new ArrayList<>(oldContact);
        expectedTable.set(index, testData.withId(oldContact.get(index).id()));
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        expectedTable.sort(compareById);
        Assertions.assertEquals(newContact, expectedTable);
    }

    @Test
    void canAddContactToGroup() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images/avatar.png")));
        }
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "group name", "group header", "group footer"));
        }
        var group = app.hbm().getGroupList().get(0);
        var contact = app.hbm().getContactList().get(0);

        if (app.hbm().isExistContactInGroup(group, contact)) {
            app.contact().removeContactFromGroup(contact, group);
        };

        var oldRelated = app.hbm().getContactsInGroup(group);
        app.contact().addContactToGroup(contact, group);
        var newRelated = app.hbm().getContactsInGroup(group);
        Assertions.assertEquals(oldRelated.size() + 1, newRelated.size());

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newRelated.sort(compareById);
        var expectedList = new ArrayList<>(oldRelated);
        expectedList.add(contact);
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newRelated);

        var newUiContactsInGroup = app.contact().getList(group);
        newUiContactsInGroup.sort(compareById);
        var expectedUiList = new ArrayList<>(expectedList);
        expectedUiList.replaceAll(contacts -> contacts.withPhoto("src/test/resources/images/avatar.png"));
        Assertions.assertEquals(expectedUiList, newUiContactsInGroup);
    }


    @Test
    public void canAddGroupInContact() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("", "1", "2", "3"));
        }
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images")));
        }

        var group = app.hbm().getGroupList().get(0);

        var oldContacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(oldContacts.size());

        app.contact().addGroup(group);

        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        var newContacts = app.hbm().getContactList();
        newContacts.sort(compareById);
        oldContacts.sort(compareById);

        Assertions.assertEquals(newContacts, oldContacts);
    }
}
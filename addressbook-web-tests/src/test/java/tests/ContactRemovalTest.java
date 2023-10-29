package tests;

import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ContactRemovalTest extends TestBase {


    @Test
    public void canRemoveContact() {
        if (app.hmb().getContactCount() == 0) {
            app.hmb().createContact(CommonFunctions.randomContact());
            app.contact().refreshPage();
            List<ContactData> oldContacts = app.hmb().getContactList();
            int index = new Random().nextInt(oldContacts.size());
            app.contact().removeContact(oldContacts.get(index));
            List<ContactData> newContacts = app.hmb().getContactList();
            List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
            expectedContacts.remove(index);
            Assertions.assertEquals(expectedContacts, newContacts);
        }
    }
    @Test
    public void canRemoveContactFromGroup() {
        if (app.jdbc().getGroupListWithContacts().isEmpty()) {
            if (app.hmb().getGroupCount() == 0) {
                app.groups().createGroup(CommonFunctions.randomGroup());
            }
            if (app.hmb().getContactCount() == 0) {
                ContactData contact = CommonFunctions.randomContact();
                GroupData group = app.hmb().getGroupList().get(0);
                app.contact().createContact(contact, group);
            } else {
                ContactData contact = app.hmb().getContactList().get(0);
                GroupData group = app.hmb().getGroupList().get(0);
                app.contact().addToGroup(contact,group);
            }
        }

        GroupData group = app.jdbc().getGroupListWithContacts().get(0);
        List<ContactData> oldRelated = app.hmb().getContactsInGroup(group);
        int index = new Random().nextInt(oldRelated.size());
        ContactData contact = oldRelated.get(index);
        app.contact().removeContactFromGroup(contact, group);
        List<ContactData> newRelated = app.hmb().getContactsInGroup(group);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        expectedRelated.remove(index);
        Assertions.assertEquals(expectedRelated, newRelated);
    }
}

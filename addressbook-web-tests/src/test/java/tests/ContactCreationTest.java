package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    public static List<ContactData> contactProvider() throws IOException {
        String json = Files.readString(Paths.get("contacts.json"));
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<>() {});
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact, GroupData group) {
        List<ContactData> oldContacts = app.hmb().getContactList();
        app.contact().createContact(contact,group);
        List<ContactData> newContacts = app.hmb().getContactList();
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newContacts.sort(compareById);
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        String id = newContacts.get(newContacts.size() - 1).id();
        expectedContacts.add(contact.withId(id).withPhoto(""));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }
    @Test
    public void canCreateContactInGroup() {
        ContactData contact = new ContactData().withFirstName(CommonFunctions.randomString(10));
        if (app.hmb().getGroupCount() == 0) {
            app.hmb().createGroup(CommonFunctions.randomGroup());
        }
        GroupData group = app.hmb().getGroupList().get(0);
        List<ContactData> oldRelated = app.hmb().getContactsInGroup(group);
        app.contact().createContact(contact, group);
        List<ContactData> newRelated = app.hmb().getContactsInGroup(group);
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newRelated.sort(compareById);
        List<ContactData> expectedRelated = new ArrayList<>(oldRelated);
        String id = newRelated.get(newRelated.size() - 1).id();
        expectedRelated.add(contact.withId(id).withPhoto(""));
        expectedRelated.sort(compareById);
        Assertions.assertEquals(expectedRelated, newRelated);
    }
}

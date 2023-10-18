package tests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.CommonFunctions;
import model.ContactData;
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
        List<ContactData> result = mapper.readValue(json, new TypeReference<>() {});
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void canCreateContact(ContactData contact) {
        List<ContactData> oldContacts = app.contact().getContactList();
        app.contact().createContact(contact);
        List<ContactData> newContacts = app.contact().getContactList();
        Comparator<ContactData> compareById = Comparator.comparingInt(o -> Integer.parseInt(o.id()));
        newContacts.sort(compareById);
        List<ContactData> expectedContacts = new ArrayList<>(oldContacts);
        String id = newContacts.get(newContacts.size() - 1).id();
        expectedContacts.add(contact.withId(id));
        expectedContacts.add(contact.withId(id).withPhoto(""));
        expectedContacts.sort(compareById);
        Assertions.assertEquals(expectedContacts, newContacts);
    }

}

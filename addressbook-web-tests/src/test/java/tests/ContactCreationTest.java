package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends TestBase{

    public static List<ContactData> contactProvider() {
        var result = new ArrayList<ContactData>();
        for (var fistName : List.of("","first name")){
            for (var lastName : List .of("","last name")){
                for (var address : List.of("","address")){
                    for (var email : List.of("","email")){
                        for (var mobile : List.of("", "mobile")){
                            result.add(new ContactData(" ", fistName, lastName, address, email,mobile, " "));
                        }
                    }

                }
            }
        }
        for (int i = 0; i < 5; i++){
            result.add(new ContactData(" ", randomString(i * 10), randomString(i * 10),randomString(i * 10),randomString(i * 10),randomString(i * 10), " "));
        }
        return result;
    }

    @ParameterizedTest
    @MethodSource("contactProvider")
    public void CanCreateMultipleContact(ContactData contact) {
        var oldContacts = app.contact().getContactList();
        app.contact().createContact(contact);
        var newContact = app.contact().getContactList();
        Comparator<ContactData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newContact.sort(compareById);
        var expectedList = new ArrayList<>(oldContacts);
        expectedList.add(contact.withId(newContact.get(newContact.size() - 1).id()).withFirstName("").withLastName(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(newContact, expectedList);
    }
    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData(" ",
                "first name",
                "last name",
                "address",
                "email",
                "phone",
                "src\\test\\resources\\images\\avatar.jpg"));
    }
}

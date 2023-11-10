package tests;

import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    public void testContact() {
        ContactData contact = app.hmb().getContactList().get(0);

        String phones = app.contact().getPhones(contact);
        String expectedPhones = Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedPhones, phones);

        String emails = app.contact().getEmails(contact);
        String expectedEmails = Stream.of(contact.email(), contact.email2(), contact.email3())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedEmails, emails);

        String address = app.contact().getAddress(contact);
        String expectedAddress = Stream.of(contact.address(), contact.address2())
                .filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining("\n"));
        Assertions.assertEquals(expectedAddress, address);
    }
}
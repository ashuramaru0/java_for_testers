package tests;

import common.CommonFunctions;
import model.ContactData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactInfoTests extends TestBase {

    @Test
    public void testContact() {
        if (app.hbm().getContactCount() == 0) {
            app.hbm().createContact(new ContactData()
                    .withFirstName(CommonFunctions.randomString(10))
                    .withLastName(CommonFunctions.randomString(10))
                    .withPhoto(randomFile("src/test/resources/images"))
                    .withHome("123456796")
                    .withWork("1234567")
                    .withMobile("123")
                    .withSecondary("12")
                    .withAddress("Address")
                    .withAddress2("Address2")
                    .withEmail("email")
                    .withEmail2("email2")
                    .withEmail3("email3"));
        }

        var contacts = app.hbm().getContactList();
        var rnd = new Random();
        var index = rnd.nextInt(contacts.size());


        var phone = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.home(), contact.mobile(), contact.work(), contact.secondary())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining("\n"))
        ));

        var phones = app.contact().getPhones();
        Assertions.assertEquals(phone, phones);


        var expected = contacts.stream().collect(Collectors.toMap(ContactData::id, contact ->
                Stream.of(contact.address(), contact.address2())
                        .filter(s -> s != null && !"".equals(s))
                        .collect(Collectors.joining(""))
        ));


        var addresses = expected.get(contacts.get(index).id());
        var interfaceAddress = app.contact().getAddresses(contacts.get(index));
        Assertions.assertEquals(addresses, interfaceAddress);


        var homePageAddress = app.contact().getAddressFromHomePage(contacts.get(index));
        var address = app.contact().getAddress(contacts.get(index));
        Assertions.assertEquals(address, homePageAddress);


        var homePageEmails = app.contact().getEmailsFromHomePage(contacts.get(index));
        var interfaceEmails = app.contact().getEmailsFromEditForm(contacts.get(index));
        Assertions.assertEquals(interfaceEmails, homePageEmails);

    }
}
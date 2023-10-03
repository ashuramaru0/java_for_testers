package tests;

import model.ContactData;
import model.GroupData;
import org.junit.jupiter.api.Test;

public class ContactCreationTest extends TestBase{
    @Test
    public void canCreateContact() {
        app.contact().createContact(new ContactData("first name", "last name", "address", "email", "phone"));
    }
}

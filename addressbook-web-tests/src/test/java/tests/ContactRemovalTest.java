package tests;

import model.ContactData;
import org.junit.jupiter.api.Test;

public class ContactRemovalTest extends TestBase{

    @Test
    public void canRemoveContact() {
        if (!app.contact().isContactPresent()){
            app.contact().createContact(new ContactData("first name", "last name", "address", "email", "phone"));
        }
        app.contact().removeContact();
    }
}

package common;

import model.ContactData;
import model.GroupData;

import java.util.Random;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        var result = "";
        for (int i = 0; i < n; i++) {
            result = result + (char) ('a' + rnd.nextInt(26));
        }
        return result;
    }
    public static ContactData randomContact() {
        Random random = new Random();
        return new ContactData()
                .withFirstName(randomString(random.nextInt(25)))
                .withLastName(randomString(random.nextInt(25)))
                .withAddress(randomString(random.nextInt(25)))
                .withEmail(randomString(random.nextInt(25)))
                .withPhone(randomString(random.nextInt(25)));
    }

    public static GroupData randomGroup() {
        Random random = new Random();
        return new GroupData()
                .withName(randomString(random.nextInt(25)))
                .withHeader(randomString(random.nextInt(25)))
                .withFooter(randomString(random.nextInt(25)));
    }
}

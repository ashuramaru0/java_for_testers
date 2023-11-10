package common;

import model.ContactData;
import model.GroupData;

import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommonFunctions {
    public static String randomString(int n) {
        var rnd = new Random();
        Supplier<Integer> randomNumbers = () -> rnd.nextInt(26);
        var result = Stream.generate(randomNumbers)
                .limit(n)
                .map(i -> 'a' + 1)
                .map(Character::toString)
                .collect(Collectors.joining());
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

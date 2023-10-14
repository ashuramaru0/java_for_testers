package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import common.CommonFunctions;
import model.ContactData;
import model.GroupData;

import java.util.ArrayList;

public class Generator {

    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() {
        var data = generate();
        save(data);
    }

    private void save(Object data) {

    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generateContacts() {
        var result = new ArrayList<ContactData>();
        for (int i = 0; i < 5; i++) {
            result.add(new ContactData(" ", CommonFunctions.randomString(i * 10),
                    CommonFunctions.randomString(i * 10),
                    CommonFunctions.randomString(i * 10),
                    CommonFunctions.randomString(i * 10),
                    CommonFunctions.randomString(i * 10),
                    " "));
        }
        return result;
    }

    private Object generateGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunctions.randomString(i * 10))
                    .withHeader(CommonFunctions.randomString(i * 10))
                    .withFooter(CommonFunctions.randomString(i * 10)));
        }
        return result;
    }
}

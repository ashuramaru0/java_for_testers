package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GroupModificationTest extends TestBase{

    @Test
    void canModifyGroup(){
        if (app.hmb().getGroupCount() == 0){
            app.hmb().createGroup(new GroupData("", "group name", "group header", "group footer"));
        };
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName("modifed name");
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);
        expectedList.sort(compareById);
        Assertions.assertEquals(newGroups,expectedList);
    }

}

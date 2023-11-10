package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTest extends TestBase{

    @Test
    void canModifyGroup(){
        if (app.hmb().getGroupCount() == 0){
            app.hmb().createGroup(CommonFunctions.randomGroup());
        };
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = CommonFunctions.randomGroup();
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        Assertions.assertEquals(Set.copyOf(newGroups),Set.copyOf(expectedList));
    }

}

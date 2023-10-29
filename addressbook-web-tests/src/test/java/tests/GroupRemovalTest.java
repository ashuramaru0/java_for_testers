package tests;

import common.CommonFunctions;
import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GroupRemovalTest extends TestBase{
    @Test
    public void canRemoveGroup() {
        if (app.hmb().getGroupCount() == 0){
            app.hmb().createGroup(CommonFunctions.randomGroup());
        };
        var oldGroups = app.hmb().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().removeGroup(oldGroups.get(index));
        var newGroups = app.hmb().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.remove(index);
        Assertions.assertEquals(newGroups, expectedList);
    }

    @Test
    void canRemoveAllGroupsAtOnce(){
        if (app.hmb().getGroupCount() == 0){
            app.hmb().createGroup(CommonFunctions.randomGroup());
        }
        app.groups().removeAllGroups();
        Assertions.assertEquals(0,app.hmb().getGroupCount());
    }

}

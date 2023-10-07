package tests;

import model.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{
  @Test
  public void CanCreateGroup() {
    int groupCount = app.groups().getCount();
    app.groups().createGroup(new GroupData("name", "group header", "group footer"));
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount + 1, newGroupCount);
  }

  @Test
  public void canCreateGroupWithEmptyName(){
    app.groups().createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnly(){
    app.groups().createGroup(new GroupData().withName("some name"));
  }

  @Test
  public void CanCreateMultipleGroup() {
    int n = 5;
    int groupCount = app.groups().getCount();
    for (int i = 0; i < n; i++){
      app.groups().createGroup(new GroupData(randomString(i), "group header", "group footer"));
    }
    int newGroupCount = app.groups().getCount();
    Assertions.assertEquals(groupCount + n, newGroupCount);
  }
}

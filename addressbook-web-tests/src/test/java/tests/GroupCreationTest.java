package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{
  @Test
  public void CanCreateGroup() {
    app.groups().createGroup(new GroupData("name", "group header", "group footer"));
  }

  @Test
  public void canCreateGroupWithEmptyName(){
    app.groups().createGroup(new GroupData());
  }

  @Test
  public void canCreateGroupWithNameOnly(){
    app.groups().createGroup(new GroupData().withName("some name"));
  }

}

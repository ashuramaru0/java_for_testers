import org.junit.jupiter.api.Test;

public class GroupCreationTest extends TestBase{
  @Test
  public void CanCreateGroup() {
    openGroupsPage();
    createGroup("group_name", "group header", "group footer");
  }

  @Test
  public void canCreateGroupWithEmptyName(){
    openGroupsPage();
    createGroup(" ", " ", " ");
  }

}
